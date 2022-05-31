package org.javacream.mockbrowser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView resultView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		resultView = (TextView) findViewById(R.id.result);
		Intent i = getIntent();
		Uri uri = i.getData();
		String uriString;
		if (uri == null || !"http".equals(uri.getScheme())){
			uriString = "http://integrata.de";
		}else{
			uriString = uri.toString();
		}
		new HttpAccessTask().execute(uriString);
	}

	class HttpAccessTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
			    HttpGet httpget = new HttpGet(params[0]);
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
			resultView.setText(result);
		}

	}

}
