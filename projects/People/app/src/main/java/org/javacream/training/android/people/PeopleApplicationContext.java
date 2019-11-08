package org.javacream.training.android.people;

import org.javacream.training.android.people.controller.CreatePersonController;
import org.javacream.training.android.people.controller.DeletePersonController;
import org.javacream.training.android.people.controller.ListPeopleController;
import org.javacream.training.android.people.model.MapPeopleModel;
import org.javacream.training.android.people.model.PeopleModel;

public class PeopleApplicationContext {

    private static MapPeopleModel peopleModel;

    public static CreatePersonController createPersonController() {
        return createPersonController;
    }
    public static DeletePersonController deletePersonController() {
        return deletePersonController;
    }
    public static ListPeopleController listPeopleController() {
        return listPeopleController;
    }

    private static CreatePersonController createPersonController;
    private static DeletePersonController deletePersonController;
    private static ListPeopleController listPeopleController;

    static{
        //Objekte parameterlos erzeugen
        peopleModel = new MapPeopleModel();
        createPersonController = new CreatePersonController();
        deletePersonController = new DeletePersonController();
        listPeopleController = new ListPeopleController();
        //Dependencies injecten = setzen
        createPersonController.setPeopleModel(peopleModel);
        deletePersonController.setPeopleModel(peopleModel);
        listPeopleController.setPeopleModel(peopleModel);
    }

    public static PeopleModel peopleModel(){
        return peopleModel;
    }
}
