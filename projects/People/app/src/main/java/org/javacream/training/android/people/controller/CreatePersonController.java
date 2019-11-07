package org.javacream.training.android.people.controller;

import android.os.AsyncTask;
import android.util.Log;

import org.javacream.training.android.people.model.PeopleModel;
import org.javacream.training.android.people.model.Person;

import java.util.Date;

public class CreatePersonController {

    private PeopleModel peopleModel;

    public void setPeopleModel(PeopleModel peopleModel) {
        this.peopleModel = peopleModel;
    }

    public void create(final String lastname, final String firstname, final Character gender, final Integer height, final CreatePersonUpdate updateFunction){
        AsyncTask<Void, Void, Person> task = new AsyncTask<Void, Void, Person>() {
            @Override
            protected Person doInBackground(Void... voids) {
                //LÄUFT IM WORKER-THREAD
                Log.i("CONTROLLER", Thread.currentThread().getName() + " before model execution" + new Date());
                Person result = peopleModel.create(lastname, firstname, gender, height);
                Log.i("CONTROLLER", Thread.currentThread().getName() + " after model execution" + new Date());

                return result;
            }

            @Override
            protected void onPostExecute(Person person) {
                //LÄUFT IM EVENT DISPATCH THREAD
                Log.i("CONTROLLER", Thread.currentThread().getName() + " in PostExecution" + new Date());
                updateFunction.handlePersonCreated(person);
            }
        };

        task.execute();
    }
}
