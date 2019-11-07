package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.javacream.training.android.people.model.PeopleModel;

public class PeopleListActivity extends AppCompatActivity {
    private PeopleModel peopleModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        peopleModel = PeopleApplicationContext.peopleModel();
        Log.i("DETAIL", peopleModel.findAll().toString());
    }
}
