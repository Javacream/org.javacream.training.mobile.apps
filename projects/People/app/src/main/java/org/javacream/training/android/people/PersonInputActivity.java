package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.javacream.training.android.people.controller.CreatePersonController;
import org.javacream.training.android.people.controller.CreatePersonUpdate;
import org.javacream.training.android.people.model.Person;

import java.util.ArrayList;

public class PersonInputActivity extends AppCompatActivity {
    private EditText lastnameInput;
    private EditText firstnameInput;
    private EditText genderInput;
    private EditText heightInput;
    private Button saveButton;
    private ArrayList<String> messages;
    private CreatePersonController createPersonController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_input);
        createFields();
        checkSaveButtonActive();
        messages = new ArrayList<>();
        createPersonController = PeopleApplicationContext.createPersonController();
    }

    private void createFields(){
        View.OnKeyListener onKeyListener = new View.OnKeyListener(){

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                checkSaveButtonActive();
                return false;
            }

        };
        lastnameInput  = this.findViewById(R.id.lastnameInput);
        firstnameInput  = this.findViewById(R.id.firstnameInput);
        genderInput  = this.findViewById(R.id.genderInput);
        heightInput  = this.findViewById(R.id.heightInput);
        saveButton = findViewById(R.id.saveButton);
        lastnameInput.setText("HELLO FROM ACTIVITY");
        lastnameInput.setOnKeyListener(onKeyListener);
        firstnameInput.setOnKeyListener(onKeyListener);
        heightInput.setOnKeyListener(onKeyListener);
        genderInput.setOnKeyListener(onKeyListener);
    }


    private void checkSaveButtonActive(){
        boolean isSaveButtonActive = ((lastnameInput.getText().length() > 0)&&(firstnameInput.getText().length() > 0)&&(genderInput.getText().length() > 0)&&(heightInput.getText().length() > 0));
        saveButton.setEnabled(isSaveButtonActive);
    }
    public void doSave(View view){
        //Beliebige Code-Sequenz
        Log.i("Action", "called doSave");
        String lastname = lastnameInput.getText().toString();
        String firstname = firstnameInput.getText().toString();
        String gender = genderInput.getText().toString();
        String height = heightInput.getText().toString();
        CreatePersonUpdate updateFunction = new CreatePersonUpdate() {
            @Override
            public void handlePersonCreated(Person created) {
                String message = "saving lastname=" + created.toString();
                messages.add(message);
            }
        };
        createPersonController.create(lastname, firstname, gender.charAt(0), Integer.parseInt(height), updateFunction);
    }
    public void doClear(View view){
        //Beliebige Code-Sequenz
        Log.i("Action", "called doClear");
        lastnameInput.setText("");
        firstnameInput.setText("");
        genderInput.setText("");
        heightInput.setText("");
        checkSaveButtonActive();
    }
    public void doShowLog(View view){
        Intent intent = new Intent(this, LogActivity.class);
        intent.putStringArrayListExtra("messages", messages);
        startActivity(intent);
    }

    public void doGoBack(View view){
        this.finish();
    }
}
