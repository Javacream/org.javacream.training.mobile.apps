package org.javacream.training.android.people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.javacream.training.android.people.model.Person;

public class PeopleArrayAdapter extends ArrayAdapter<Person> {
    public PeopleArrayAdapter(Context context, Person[] objects) {
        super(context, R.layout.person_row_layout, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_row_layout, parent, false);
        }
        TextView lastname = (TextView) convertView.findViewById(R.id.lastnameInRow);
        TextView firstname = (TextView) convertView.findViewById(R.id.firstnameInRow);
        lastname.setText(person.getLastname());
        firstname.setText(person.getFirstname());
        return convertView;
    }

}
