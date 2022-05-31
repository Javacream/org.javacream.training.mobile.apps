package org.javacream.android.resources;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DbActivity extends Activity {

	private EditText sqlStatementInput;
	private TextView sqlResultOutput;
	private String dbErrorPrefix;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db);
		sqlStatementInput = (EditText) findViewById(R.id.dbStatementInput);
		sqlResultOutput = (TextView) findViewById(R.id.dbResultOutput);
		dbErrorPrefix = getString(R.string.dbErrorPrefix);
	}

	public void prepareDb(View view) {
		SQLiteDatabase database = null;
		try {
			database = openOrCreateDatabase("testDb", Context.MODE_PRIVATE,
					null);
			database.execSQL("drop table if exists PEOPLE");
			database.execSQL("create table PEOPLE(id long, lastname varchar(255), firstname varchar(255), primary key (id))");
			for (int i = 0; i < 100; i++) {
				Object[] params = { i, "Firstname" + i, "Lastname" + i };
				database.execSQL(
						"insert into PEOPLE (id, firstname, lastname) values(?, ?, ?)",
						params);
			}
		} catch (RuntimeException e) {
			String message = e.toString();
			Log.e("DbActivity", message);
			Toast.makeText(this, dbErrorPrefix + message, 2000).show();
		} finally {
			if (database != null) {
				database.close();
			}
		}
	}

	public void executeSqlStatement(View view) {
		SQLiteDatabase database = null;
		try {
			database = openOrCreateDatabase("testDb", Context.MODE_PRIVATE,
					null);
			String statement = sqlStatementInput.getText().toString();
			Cursor personCursor = database.rawQuery(statement, null);
			String result = "";
			while (personCursor.moveToNext()) {
				result += ("Found Person: id=" + personCursor.getLong(0)
						+ ", firstname=" + personCursor.getString(1)
						+ ", lastname=" + personCursor.getString(2) + '\n');
			}
			sqlResultOutput.setText(result);
		} catch (SQLiteException e) {
			String message = e.toString();
			Log.e("DbActivity", message);
			Toast.makeText(this, dbErrorPrefix + message, 2000).show();
		} finally {
			if (database != null) {
				database.close();
			}
		}

	}
}