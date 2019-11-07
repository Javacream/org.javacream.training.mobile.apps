package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PeopleMainActivity extends AppCompatActivity {

    private EditText lastnameInput;
    private EditText firstnameInput;
    private EditText genderInput;
    private EditText heightInput;
    private LinearLayout logView;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Lifecycle", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_input);
        logView = (LinearLayout) getLayoutInflater().inflate(R.layout.log_layout, null);
        logView.setOrientation(LinearLayout.VERTICAL);
        retrieveFields();
    }

    private void retrieveFields(){
        lastnameInput  = this.findViewById(R.id.lastnameInput);
        firstnameInput  = this.findViewById(R.id.firstnameInput);
        genderInput  = this.findViewById(R.id.genderInput);
        heightInput  = this.findViewById(R.id.heightInput);
        lastnameInput.setText("HELLO FROM ACTIVITY");
        saveButton = findViewById(R.id.saveButton);
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
    }
    public void doShowLog(View view){
        setContentView(logView);
    }
    public void doShowPersonInput(View view){
        setContentView(R.layout.person_input);
        retrieveFields();
    }
}
