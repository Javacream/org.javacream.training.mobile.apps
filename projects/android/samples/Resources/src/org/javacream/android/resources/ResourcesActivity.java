package org.javacream.android.resources;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResourcesActivity extends Activity {
    /** Called when the activity is first created. */
	public static final String LOG_CATEGORY = "Ressources";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    

    public void callSerializationService(View view){
    	ArrayList<String> strings = new ArrayList<String>();
    	strings.add("Hugo");
    	strings.add("Emil");
    	strings.add("Fritz");
		Intent intent = new Intent("write");
		intent.putExtra("filename", "demo_resource.ser");
		intent.putExtra("serializable", strings);
		this.startService(intent);

    }

    public void showThreadingUi(View view){
		Intent intent = new Intent(this, ThreadingActivity.class);
		startActivity(intent);

    }
    public void showPreferencesUi(View view){
		Intent intent = new Intent(this, PreferencesActivity.class);
		startActivity(intent);

    }
    public void showEncryptedPreferencesUi(View view){
		Intent intent = new Intent(this, EncryptActivity.class);
		startActivity(intent);

    }
    public void showFileIoUi(View view){
		Intent intent = new Intent(this, FileIoActivity.class);
		startActivity(intent);

    }
    public void showDbUi(View view){
		Intent intent = new Intent(this, DbActivity.class);
		startActivity(intent);

    }
    public void showHttpAccessUi(View view){
		Intent intent = new Intent(this, HttpAccessActivity.class);
		startActivity(intent);

    }
}