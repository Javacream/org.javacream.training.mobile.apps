package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.javacream.training.android.people.controller.DeletePersonController;
import org.javacream.training.android.people.model.PeopleModel;
import org.javacream.training.android.people.model.Person;

import java.util.List;

public class PeopleListActivity extends AppCompatActivity implements DeletePersonController.UpdateCallback {
    private PeopleModel peopleModel;
    private PeopleArrayAdapter peopleArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        peopleModel = PeopleApplicationContext.peopleModel();
        Log.i("DETAIL", peopleModel.findAll().toString());
        List<Person> peopleList = peopleModel.findAll();
        peopleArrayAdapter = new PeopleArrayAdapter(this, peopleList);
        ListView peopleListView = findViewById(R.id.peopleListView);
        peopleListView.setAdapter(peopleArrayAdapter);
    }

    @Override
    public void updateDeletePerson() {
        peopleArrayAdapter.notifyDataSetChanged();
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
            TextView lastname = convertView.findViewById(R.id.lastnameInRow);
            TextView firstname = convertView.findViewById(R.id.firstnameInRow);
            lastname.setText(person.getLastname());
            firstname.setText(person.getFirstname());
            return convertView;
        }

    }
}
