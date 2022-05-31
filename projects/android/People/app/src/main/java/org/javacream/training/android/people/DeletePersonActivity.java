package org.javacream.training.android.people;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.javacream.training.android.people.controller.DeletePersonController;

public class DeletePersonActivity extends AppCompatActivity {
    private DeletePersonController deletePersonController;
    private EditText idInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_person);
        deletePersonController = PeopleApplicationContext.deletePersonController();
        idInput = findViewById(R.id.idInputForDelete);
    }

    public void doDelete(View view) {
        final Long id = Long.parseLong(idInput.getText().toString());
        DeletePersonController.UpdateCallback callback = new DeletePersonController.UpdateCallback() {
            @Override
            public void updateDeletePerson() {
                Toast.makeText(DeletePersonActivity.this, "deleted a person with id " + id, Toast.LENGTH_LONG).show();
                finish();
            }
        };
        deletePersonController.deletePerson(id, callback);
    }
}
