package org.javacream.android.resources;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PreferencesActivity extends Activity {

	private Button showPreferenceButton;
	private Button incrementPreferenceButton;

	private int preference;
	private SharedPreferences prefs;
	private static final String PREF_NAME = "aNumber";

	@Override
	protected void onStart() {
		super.onStart();
		prefs = getPreferences(MODE_PRIVATE);
		preference = prefs.getInt(PREF_NAME, 0);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Editor editor = prefs.edit();
		editor.putInt(PREF_NAME, preference);
		editor.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences);
		showPreferenceButton = (Button) findViewById(R.id.showPreferenceButton);
		incrementPreferenceButton = (Button) findViewById(R.id.incrementPreferenceButton);

		showPreferenceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(PreferencesActivity.this,
						"PREFERENCE is " + preference, Toast.LENGTH_LONG)
						.show();

			}
		});
		incrementPreferenceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				preference++;

			}
		});
	}

}