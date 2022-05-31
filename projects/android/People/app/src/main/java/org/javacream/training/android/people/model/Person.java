package org.javacream.training.android.people.model;


import java.io.Serializable;

public class Person implements Serializable {
    private long id;
    private String lastname;
    private String firstname;
    private Character gender;
    private Integer height;

    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", gender=" + gender +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (lastname != null ? !lastname.equals(person.lastname) : person.lastname != null)
            return false;
        if (firstname != null ? !firstname.equals(person.firstname) : person.firstname != null)
            return false;
        if (gender != null ? !gender.equals(person.gender) : person.gender != null) return false;
        return height != null ? height.equals(person.height) : person.height == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        return result;
    }

    public Person(long id, String lastname, String firstname, Character gender, Integer height) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.gender = gender;
        this.height = height;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


}