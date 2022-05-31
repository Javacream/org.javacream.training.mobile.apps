package org.javacream.android.navigation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class NavigationActivity extends Activity {
	private EditText intentUrlView;
	private EditText freeActionInput;
	private RadioButton optionPick;
	private RadioButton optionEdit;
	private RadioButton optionView;
	private RadioButton optionFreeAction;
	private RadioButton optionWithResult;
	private RadioButton optionAsync;
	private Button navigateButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_main);
		freeActionInput = (EditText) findViewById(R.id.freeActionInputText);
		intentUrlView = (EditText) findViewById(R.id.intentUrl);
		optionEdit = (RadioButton) findViewById(R.id.ActionEdit);
		optionPick = (RadioButton) findViewById(R.id.ActionPick);
		optionView = (RadioButton) findViewById(R.id.ActionView);
		optionFreeAction = (RadioButton) findViewById(R.id.FreeAction);
		intentUrlView.setText(getString(R.string.intentDefaultUrl));
		optionWithResult = (RadioButton) findViewById(R.id.Sync);
		optionAsync = (RadioButton) findViewById(R.id.Async);
		navigateButton = (Button) findViewById(R.id.navigationButton);

		navigateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					navigate(v);
				} catch (Exception e) {
					Toast.makeText(NavigationActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public void navigate(View view) {

		String intentUrl = intentUrlView.getText().toString();
		String intentAction = "";
		if (optionEdit.isChecked()) {
			intentAction = Intent.ACTION_EDIT;
		} else if (optionPick.isChecked()) {
			intentAction = Intent.ACTION_PICK;
		} else if (optionView.isChecked()) {
			intentAction = Intent.ACTION_VIEW;
		} else if (optionFreeAction.isChecked()) {
			String freeAction = freeActionInput.getText().toString().trim();
			if (freeAction != "") {
				intentAction = freeAction;
			}
		}
		Toast.makeText(
				this,
				"Starting activity for action=" + intentAction + ", url="
						+ intentUrl, Toast.LENGTH_LONG).show();
		Intent intent = new Intent(intentAction, Uri.parse(intentUrl));

		try {

			if (optionAsync.isChecked()) {
				this.startActivity(intent);
			} else if (optionWithResult.isChecked()) {
				this.startActivityForResult(intent, 1);
			}
		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Error sending intent", e);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			Toast.makeText(this, data.getDataString(), Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Error retrieving result", e);
		}
	}
}