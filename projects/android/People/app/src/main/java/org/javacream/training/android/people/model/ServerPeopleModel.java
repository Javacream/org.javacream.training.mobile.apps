package org.javacream.training.android.people.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerPeopleModel implements PeopleModel {

    private static final String SERVER_ENDPOINT = "http://h2908727.stratoserver.net:8080/people";

    public void init() {
    }

    @Override
    public Person create(String lastname, String firstname, Character gender, Integer height) {
        Person person = new Person(0, lastname, firstname, gender, height);
        savePerson(person);
        return person;
    }

    @Override
    public void deleteById(long id) {
        deletePerson(id);

    }

    @Override
    public Person findById(long id) {
        try {
            URL url = new URL(SERVER_ENDPOINT + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading response from input Stream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String output;
                StringBuffer response = new StringBuffer();

                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();

                String responseText = response.toString();
                JSONObject jsonObject = new JSONObject(responseText);
                Person found = new Person();
                found.setId(jsonObject.getLong("id"));
                found.setLastname(jsonObject.getString("lastname"));
                found.setFirstname(jsonObject.getString("firstname"));
                found.setHeight(jsonObject.getInt("height"));
                found.setGender(jsonObject.getString("gender").charAt(0));
                return found;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public void update(Person p) {
        updatePerson(p);
    }


    private void deletePerson(Long id) {
        try {
            URL url = new URL(SERVER_ENDPOINT + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("DELETE");
            conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void savePerson(Person person) {
        JSONObject object = new JSONObject();
        try {
            object.put("lastname", person.getLastname());
            object.put("firstname", person.getFirstname());
            object.put("gender", person.getGender());
            object.put("height", person.getHeight());
            object.put("id", person.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String personJson = object.toString();
        try {
            URL url = new URL(SERVER_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
            outputStreamWriter.write(personJson);
            outputStreamWriter.flush();
            int responseCode = conn.getResponseCode();
            Log.i("TEST", "ResponseCode=" + responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updatePerson(Person person) {
        JSONObject object = new JSONObject();
        try {
            object.put("lastname", person.getLastname());
            object.put("firstname", person.getFirstname());
            object.put("gender", person.getGender());
            object.put("height", person.getHeight());
            object.put("id", person.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String personJson = object.toString();
        try {
            URL url = new URL(SERVER_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
            outputStreamWriter.write(personJson);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            int responseCode = conn.getResponseCode();
            Log.i("TEST", "ResponseCode=" + responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Person> findAll() {
        ArrayList<Person> people = new ArrayList<>();
        try {
            URL url = new URL(SERVER_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading response from input Stream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String output;
                StringBuffer response = new StringBuffer();

                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();

                String responseText = response.toString();
                JSONArray jsonArray = new JSONArray(responseText);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Person p = new Person();
                    p.setId(object.getLong("id"));
                    p.setLastname(object.getString("lastname"));
                    p.setFirstname(object.getString("firstname"));
                    p.setGender(object.getString("gender").charAt(0));
                    p.setHeight(object.getInt("height"));
                    people.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }


    @Override
    public List<Person> findByLastname(String lastname) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person p : this.findAll()) {
            if (p.getLastname().startsWith(lastname)) {
                result.add(p);
            }
        }
        return result;
    }

}