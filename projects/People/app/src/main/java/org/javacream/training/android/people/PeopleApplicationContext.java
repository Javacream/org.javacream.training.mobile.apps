package org.javacream.training.android.people;

import org.javacream.training.android.people.model.MapPeopleModel;
import org.javacream.training.android.people.model.PeopleModel;

public class PeopleApplicationContext {

    private static MapPeopleModel peopleModel;

    static{
        peopleModel = new MapPeopleModel();
    }

    public static PeopleModel peopleModel(){
        return peopleModel;
    }
}
