package org.javacream.training.android.people.controller;

import android.os.AsyncTask;

import org.javacream.training.android.people.model.PeopleModel;
import org.javacream.training.android.people.model.Person;


public class CreatePersonController {

    private PeopleModel peopleModel;

    public void setPeopleModel(PeopleModel peopleModel) {
        this.peopleModel = peopleModel;
    }

    public void create(final String lastname, final String firstname, final Character gender, final Integer height, final CreatePersonUpdate updateFunction){
        AsyncTask<Void, Void, Person> task = new AsyncTask<Void, Void, Person>() {
            @Override
            protected Person doInBackground(Void... voids) {
                Person result = peopleModel.create(lastname, firstname, gender, height);

                return result;
            }

            @Override
            protected void onPostExecute(Person person) {
                updateFunction.handlePersonCreated(person);
            }
        };

        task.execute();
    }
}
