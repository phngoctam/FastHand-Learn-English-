package com.example.game1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertDatabase extends Activity {
	WordDAO word;
	EditText et;
	EditText etidtopic;
	
	Button btn;
	String content;
	String picture;
	String sound;
	int idtopic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_database);
		
	
		
		et = (EditText) findViewById(R.id.editText1Insert);
		etidtopic = (EditText) findViewById(R.id.editText1InsertIDTOPIC);
		
		btn = (Button) findViewById(R.id.btnInsertdata);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				solveSubmit();
				Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
				et.setText("");
				et.requestFocus();

			}
		});
	}
	private void solveSubmit() {

		word = new WordDAO(this);
		idtopic = Integer.parseInt(etidtopic.getText().toString().trim());
		content = et.getText().toString().trim();
		picture = content +".jpg";
		sound = content +".mp3";
		word.insertWordToDatabase(idtopic, content, picture, sound);
		Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert_database, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
