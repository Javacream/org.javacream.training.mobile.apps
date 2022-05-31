package org.javacream.training.android.people.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PeopleTest {

    private MapPeopleModel peopleModel;

    @Before
    public void init() {
        peopleModel = new MapPeopleModel();
        for (int i = 0; i < 11; i++) {
            Character gender;
            if (i % 2 == 0) {
                gender = 'm';
            } else {
                gender = 'f';
            }
            peopleModel.create("ln" + i, "fn" + i, gender, i + 170);
        }
    }

    @Test
    public void testCreatePerson() {
        String lastname = "E";
        String firstname = "W";
        Character gender = 'f';
        Integer height = 177;
        Person p = peopleModel.create(lastname, firstname, gender, height);
        assertEquals(lastname, p.getLastname());
        assertEquals(firstname, p.getFirstname());
        assertEquals(gender, p.getGender());
        assertEquals(height, p.getHeight());
    }

    @Test
    public void testDeletePerson() {
        Person p = peopleModel.findById(1l);
        assertNotNull(p);
        peopleModel.deleteById(1l);
        p = peopleModel.findById(1l);
        assertNull(p);
    }

    @Test
    public void testFindPerson() {
        Person p = peopleModel.findById(1l);
        assertNotNull(p);
    }

    @Test
    public void testFindByLastnameAllMuster() {
        List<Person> musterPeople = peopleModel.findByLastname("Muster");
        assertEquals(5, ((List) musterPeople).size());
    }

    @Test
    public void testFindByLastnameOneMuster() {
        List<Person> musterPeople = peopleModel.findByLastname("Muster2");
        assertEquals(1, ((List) musterPeople).size());
    }

    @Test
    public void testFindByLastnameUnknown() {
        List<Person> musterPeople = peopleModel.findByLastname("UNKNOWN");
        assertEquals(0, ((List) musterPeople).size());
    }

}