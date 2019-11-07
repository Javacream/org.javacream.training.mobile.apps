package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.javacream.training.android.people.model.PeopleModel;
import org.javacream.training.android.people.model.Person;

import java.util.List;

public class PeopleListActivity extends AppCompatActivity {
    private PeopleModel peopleModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        peopleModel = PeopleApplicationContext.peopleModel();
        Log.i("DETAIL", peopleModel.findAll().toString());
        List<Person> peopleList = peopleModel.findAll();
        Person[] peopleAsArray = new Person[peopleList.size()];
        peopleList.toArray(peopleAsArray);
        PeopleArrayAdapter peopleArrayAdapter = new PeopleArrayAdapter(this, peopleAsArray);
        ListView peopleListView = findViewById(R.id.peopleListView);
        peopleListView.setAdapter(peopleArrayAdapter);
    }
}
