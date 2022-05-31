package org.javacream.servicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Activity3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);
		Toast.makeText(this, "service finished", Toast.LENGTH_LONG).show();
	}

}
