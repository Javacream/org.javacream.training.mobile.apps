package org.javacream.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class NotRecommendedSimpleService extends IntentService {

	
	public NotRecommendedSimpleService() {
		super("SimpleService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("SimpleService", "simple service startet");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Log.e("SimpleService", "InterruptedException", e);
		}
		Log.i("SimpleService", "simple service finished, start redirect");
		Intent redirect = new Intent(this, Activity3.class);
		redirect.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(redirect);
		Log.i("SimpleService", "simple service finished, redirect done");
		
	}

}
