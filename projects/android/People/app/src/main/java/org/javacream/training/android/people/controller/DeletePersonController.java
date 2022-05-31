package org.javacream.training.android.people.controller;

import android.os.AsyncTask;

import org.javacream.training.android.people.model.PeopleModel;

public class DeletePersonController {
    public void setPeopleModel(PeopleModel peopleModel) {
        this.peopleModel = peopleModel;
    }

    private PeopleModel peopleModel;

    public void deletePerson(final long id, final UpdateCallback callback) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                peopleModel.deleteById(id);
                return null;
            }

            @Override
            protected void onPostExecute(Void voids) {
                callback.updateDeletePerson();
            }
        }.execute();


    }

    public interface UpdateCallback {
        public void updateDeletePerson();
    }
}
