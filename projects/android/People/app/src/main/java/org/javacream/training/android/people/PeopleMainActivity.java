package org.javacream.training.android.people;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PeopleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_main);
    }

    public void doNavigationToPersonInput(View view) {
        Intent intent = new Intent(this, PersonInputActivity.class);
        this.startActivity(intent);
    }

    public void showServerWebSite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://GitHub.com/Javacream/org.javacream.training.android"));
        this.startActivity(intent);
    }

    public void doNavigationToPeopleList(View view) {
        Intent intent = new Intent(this, PeopleListActivity.class);
        this.startActivity(intent);

    }

    public void doNavigationToDeletePerson(View view) {
        Intent intent = new Intent(this, DeletePersonActivity.class);
        this.startActivity(intent);
    }

}
