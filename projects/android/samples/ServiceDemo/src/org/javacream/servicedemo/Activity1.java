package org.javacream.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
	}

	public void startService(View v){
		//Intent serviceIntent = new Intent(this, NotRecommendedSimpleService.class);
		Intent serviceIntent = new Intent(this, NotifyingSimpleService.class);
		startService(serviceIntent);
		Intent redirect = new Intent(this, Activity2.class);
		startActivity(redirect);
		
	}
}
