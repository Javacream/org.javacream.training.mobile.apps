package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
        LinearLayout logView = findViewById(R.id.logView);
        Intent intent = getIntent();
        ArrayList<String> messages = intent.getStringArrayListExtra("messages");
        for (String message: messages) {
            TextView textView = new TextView(this);
            textView.setText(message);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            logView.addView(textView);
        }
    }

    public void doGoBack(View view){
        finish();
    }

}
