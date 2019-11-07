package org.javacream.training.android.people.model;

import java.util.List;
import java.util.Set;

public interface PeopleModel {
    Person create(String lastname, String firstname, Character gender, Integer height);

    void deleteById(long id);

    Person findById(long id);

    void update(Person p);

    List<Person> findAll();

    List<Person> findByLastname(String lastname);


}