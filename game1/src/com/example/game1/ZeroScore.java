package com.example.game1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ZeroScore extends Activity {

	Intent callerIntent;
	Bundle packageFromCaller;
	Intent myIntent;
	Bundle bundle;
	ScoreDAO scoreDAO;
	WordDAO word;
	ArrayList<InfoPlayer> Players;
	int idtopic;
	int score;
	String time;
	String nametopic;
	
	Button btnBackzero;
	Button btnAgainzero;
	TextView txtNameTopic;
	MyArrayAdapter adapter = null;
	ListView lvPlayerzero;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zero_score);
		Players = new ArrayList<InfoPlayer>();
		getGIFTPackageIntent();
		initviews();
		

		nametopic = getNameTopic(idtopic);
		txtNameTopic.setText(nametopic.toUpperCase() +" SCORE");
		
		loadData();
		btnBackzero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ZeroScore.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
		btnAgainzero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myIntent = new Intent(ZeroScore.this, Fast.class);
				bundle = new Bundle();
				bundle.putInt("idtopic", idtopic);
				myIntent.putExtra("MyPackage", bundle);
				startActivity(myIntent);
				
			}
		});
		
	}
	private void initviews()
	{	btnBackzero = (Button) findViewById(R.id.btnBackZero);
		btnAgainzero = (Button) findViewById(R.id.btnAgainZero);
		lvPlayerzero = (ListView) findViewById(R.id.lvPlayersZero);
		txtNameTopic = (TextView)findViewById(R.id.txtNameTopic);
	}
	public String getNameTopic(int idtopic) {
		word = new WordDAO(this);
		String nameTopic = word.getNameTopicByID(idtopic);
		return nameTopic;
	}
	private void getGIFTPackageIntent() {
		// Get Gift from score hihi
		callerIntent = getIntent();
		packageFromCaller = callerIntent.getBundleExtra("MyPackage");
		idtopic = packageFromCaller.getInt("idtopic");
		score = packageFromCaller.getInt("kqscore");
		time = packageFromCaller.getString("kqtime");
	}
	public void loadData() {
		scoreDAO = new ScoreDAO(this);
		Players = scoreDAO.getData(idtopic);
		adapter = new MyArrayAdapter(this, R.layout.my_item_listview, Players,
				score, time);
		lvPlayerzero.setAdapter(adapter);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zero_score, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
	        && (keyCode == KeyEvent.KEYCODE_BACK    || keyCode == KeyEvent.KEYCODE_HOME)
	    && event.getRepeatCount() == 0) 
	    {
	        onBackPressed();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		return;
		
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
