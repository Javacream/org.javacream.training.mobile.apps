package org.javacream.android.resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FileIoActivity extends Activity {

	private Button readFileButton;
	private Button writeFileButton;
	private EditText filenameInput;
	private EditText contentInput;
	private TextView contentOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_io);
		readFileButton = (Button) findViewById(R.id.readFile);
		writeFileButton = (Button) findViewById(R.id.writeFile);
		filenameInput = (EditText) findViewById(R.id.filenameInput);
		contentInput = (EditText) findViewById(R.id.contentInput);
		contentOutput = (TextView) findViewById(R.id.contentOutput);

		writeFileButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				writeFile();

			}
		});
		readFileButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				readFile();

			}
		});
	}

	private void readFile() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = openFileInput(filenameInput.getText().toString());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String content = bufferedReader.readLine();
			contentOutput.setText(content);
			
//			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//			people = (List<Person>)objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			Log.e("CreateFile", e.getLocalizedMessage());
		} catch (IOException e) {
			Log.e("CreateFile", e.getLocalizedMessage());
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// swallow
				}
			}
		}
	}

	private void writeFile() {
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(filenameInput.getText().toString(),
					Context.MODE_PRIVATE);
			fos.write(contentInput.getText().toString().getBytes());
			
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
//			objectOutputStream.writeObject(personList);
			
		} catch (FileNotFoundException e) {
			Log.e("CreateFile", e.getLocalizedMessage());
		} catch (IOException e) {
			Log.e("CreateFile", e.getLocalizedMessage());
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// swallow
				}
			}
		}
	}



}