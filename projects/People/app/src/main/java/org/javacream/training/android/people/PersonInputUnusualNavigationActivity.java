package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonInputUnusualNavigationActivity extends AppCompatActivity {

    private EditText lastnameInput;
    private EditText firstnameInput;
    private EditText genderInput;
    private EditText heightInput;
    private Button saveButton;
    private LinearLayout logView;
    private View inputView;

    {
        //DON'T DO ANYTHING INSIDE AN ACTIVITIES CONSTRUCTOR
        Log.i("Lifecycle", "instance constructor");
        //setContentView(R.layout.person_input); //NEVER DO THAT IN CONSTRUCTORS
        //EditText lastnameInputNull  = this.findViewById(R.id.lastnameInput);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Lifecycle", "onCreate");
        super.onCreate(savedInstanceState);
        inputView = getLayoutInflater().inflate(R.layout.person_input, null);
        logView = (LinearLayout) getLayoutInflater().inflate(R.layout.log_layout, null);
        logView.setOrientation(LinearLayout.VERTICAL);
        setContentView(inputView);
        createFields();
        checkSaveButtonActive();
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
        lastnameInput.setText("HELLO FROM DEMO ACTIVITY");
        lastnameInput.setOnKeyListener(onKeyListener);
        firstnameInput.setOnKeyListener(onKeyListener);
        heightInput.setOnKeyListener(onKeyListener);
        genderInput.setOnKeyListener(onKeyListener);
    }


    private void checkSaveButtonActive(){
        boolean isSaveButtonActive = ((lastnameInput.getText().length() > 0)&&(firstnameInput.getText().length() > 0)&&(genderInput.getText().length() > 0)&&(heightInput.getText().length() > 0));
        saveButton.setEnabled(isSaveButtonActive);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart");
    }

    @Override
    protected void onStop() {
        Log.i("Lifecycle", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Lifecycle", "onDestroy");
        super.onDestroy();
    }

    public void doSave(View view){
        //Beliebige Code-Sequenz
        Log.i("Action", "called doSave");
        String lastname = lastnameInput.getText().toString();
        String firstname = firstnameInput.getText().toString();
        String gender = genderInput.getText().toString();
        String height = heightInput.getText().toString();
        String message = "saving lastname=" + lastname + ", firstname=" + firstname + ", height=" + height + ", gender=" + gender;
        Log.i("Action", message);
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        logView.addView(textView);
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
        setContentView(logView);
    }
    public void doGoBack(View view){
        setContentView(inputView);
    }
}
