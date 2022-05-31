package org.javacream.android.resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HttpAccessActivity extends Activity {

	private EditText httpUrlInput;
	private TextView httpRequestOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_access);
		httpUrlInput = (EditText) findViewById(R.id.httpUrlInput);
		httpRequestOutput = (TextView) findViewById(R.id.httpAccessResult);
		httpUrlInput.setText(getString(R.string.accessUrl));
	}

	public void doHttpAccess(View view) {
		HttpAccessTask httpAccessTask = new HttpAccessTask();
		httpAccessTask.execute(httpUrlInput.getText().toString());
	}

	class HttpAccessTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
			    HttpGet httpget = new HttpGet("http://" + params[0]);
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				String result = "";
				if (entity != null) {
					InputStream instream = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader((instream)));
					String line;
					while ((line = reader.readLine()) != null) {
						result += line;
					}
				}
				return result;
			} catch (Exception e) {
				Log.e("HttpAccess", e.getMessage(), e);
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			httpRequestOutput.setText(result);
		}

	}
}