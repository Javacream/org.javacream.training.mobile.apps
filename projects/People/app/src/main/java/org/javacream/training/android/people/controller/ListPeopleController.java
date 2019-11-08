package org.javacream.training.android.people.controller;

import android.os.AsyncTask;

import org.javacream.training.android.people.model.PeopleModel;
import org.javacream.training.android.people.model.Person;

import java.util.List;

public class ListPeopleController {
    public void setPeopleModel(PeopleModel peopleModel) {
        this.peopleModel = peopleModel;
    }

    private PeopleModel peopleModel;

    public void listPeople(final UpdateCallback callback){
        new AsyncTask<Void, Void, List<Person>>(){

            @Override
            protected List<Person> doInBackground(Void... voids) {
                return peopleModel.findAll();
            }

            @Override
            protected void onPostExecute(List<Person> people) {
                callback.updateListPeople(people);
            }
        }.execute();


    }
    public interface UpdateCallback {
        public void updateListPeople(List<Person> people);
    }
}