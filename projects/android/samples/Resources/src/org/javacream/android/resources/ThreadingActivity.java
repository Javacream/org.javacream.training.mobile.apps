package org.javacream.android.resources;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import static org.javacream.android.resources.ResourcesActivity.LOG_CATEGORY;

public class ThreadingActivity extends Activity {

	private Button blockingLongRunningAction;
	private Button wrongThreadingLongRunningAction;
	private Button asyncLongRunningAction;
	private CheckBox showProgressDialogCheckBox;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threading);
		showProgressDialogCheckBox = (CheckBox) findViewById(R.id.showProgressDialog);
		blockingLongRunningAction = (Button) findViewById(R.id.blockingLongRunningAction);
		blockingLongRunningAction.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgressDialog dialog = null;
				if (showProgressDialogCheckBox.isChecked()) {
					dialog = ProgressDialog.show(ThreadingActivity.this, "",
							"Loading. Please wait...", true);
				}
				delay();
				if (dialog != null) {
					dialog.dismiss();
				}
				showConfirmation();
			}
		});

		wrongThreadingLongRunningAction = (Button) findViewById(R.id.wrongThreadingLongRunningAction);
		wrongThreadingLongRunningAction
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (showProgressDialogCheckBox.isChecked()) {
							dialog = ProgressDialog.show(
									ThreadingActivity.this, "",
									"Loading. Please wait...", true);
						}
						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								delay();
								if (dialog != null) {
									dialog.dismiss();
									dialog = null;
								}
								try {
									showConfirmation();
								} catch (Exception e) {
									Log.e(LOG_CATEGORY, e.getMessage());
								}
							}

						});
						thread.start();
					}
				});
		asyncLongRunningAction = (Button) findViewById(R.id.asyncLongRunningAction);
		asyncLongRunningAction.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (showProgressDialogCheckBox.isChecked()) {
					dialog = ProgressDialog.show(ThreadingActivity.this, "",
							"Loading. Please wait...", true);
				}
				BlockingAsynTask blockingAsynTask = new BlockingAsynTask();
				blockingAsynTask.execute("");
			}
		});

	}

	private void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void showConfirmation() {
		Toast toast = Toast.makeText(ThreadingActivity.this,
				"Finished long running action", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
		toast.show();
	}

	class BlockingAsynTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			delay();
//			if (dialog != null) {
//				dialog.dismiss();
//				dialog = null;
//			}
			
			this.publishProgress(42);
			return "OK";
		}

		@Override
		protected void onPostExecute(String result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			showConfirmation();
		}
	}
	
	
	
}
