package org.javacream.training.android.people;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.javacream.training.android.people.controller.DeletePersonController;
import org.javacream.training.android.people.controller.ListPeopleController;
import org.javacream.training.android.people.model.Person;

import java.util.List;

public class PeopleListActivity extends AppCompatActivity implements DeletePersonController.UpdateCallback, ListPeopleController.UpdateCallback {
    private ListPeopleController listPeopleController;
    private PeopleArrayAdapter peopleArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        ListView peopleListView = findViewById(R.id.peopleListView);
        peopleListView.setAdapter(peopleArrayAdapter);
        Intent intent = getIntent();
        List<Person> people = (List<Person>) intent.getSerializableExtra("people");
        if (people == null) {
            listPeopleController = PeopleApplicationContext.listPeopleController();
            listPeopleController.listPeople(this);
        } else {
            updateListPeople(people);
        }
    }

    @Override
    public void updateDeletePerson() {
        peopleArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateListPeople(List<Person> people) {
        peopleArrayAdapter = new PeopleArrayAdapter(this, people);
        ((ListView) findViewById(R.id.peopleListView)).setAdapter(peopleArrayAdapter);
    }

    public class PeopleArrayAdapter extends ArrayAdapter<Person> {

        public PeopleArrayAdapter(Context context, List<Person> objects) {
            super(context, R.layout.person_row_layout, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Person person = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_row_layout, parent, false);
            }
            convertView.findViewById(R.id.deletePersonButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(person);
                    PeopleApplicationContext.deletePersonController().deletePerson(person.getId(), PeopleListActivity.this);
                }
            });
            TextView lastname = (TextView) convertView.findViewById(R.id.lastnameInRow);
            TextView firstname = (TextView) convertView.findViewById(R.id.firstnameInRow);
            lastname.setText(person.getLastname());
            firstname.setText(person.getFirstname());
            return convertView;
        }

    }
}
