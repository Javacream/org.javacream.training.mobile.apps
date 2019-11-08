package org.javacream.training.android.people.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapPeopleModel implements PeopleModel {
    private HashMap<Long, Person> people;

    private Long counter = 100l;
    {
        final long CREATE_PEOPLE_NUMBER = 5;
        people = new HashMap<>();
        for (long i = 0; i < CREATE_PEOPLE_NUMBER; i++){
            Person p = new Person();
            p.setId(i);
            p.setLastname("Muster" + i);
            p.setFirstname("Mann" + i);
            p.setGender('X');
            p.setHeight(170 + (int)i);
            people.put(i, p);
        }
        counter = CREATE_PEOPLE_NUMBER;
    }

    @Override
    public Person create(String lastname, String firstname, Character gender, Integer height){
        Person person = new Person(counter++, lastname, firstname, gender, height);
        people.put(person.getId(), person);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void deleteById(long id){
        people.remove(id);
    }

    @Override
    public Person findById(long id){
        return people.get(id);
    }

    @Override
    public void update(Person p){
        people.put(p.getId(), p);
    }

    @Override
    public List<Person> findAll(){
        return new ArrayList<>(people.values());
    }

    @Override
    public List<Person> findByLastname(String lastname) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person p: people.values()){
            if (p.getLastname().startsWith(lastname)){
                result.add(p);
            }
        }
        return result;
    }

}