package com.example.game1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Score extends Activity {

	ScoreDAO scoreDAO;

	ArrayList<InfoPlayer> Players;
	ArrayList<InfoPlayer> loadPlayers;

	MyArrayAdapter adapter = null;
	ListView lvPlayer;

	Intent callerIntent;
	Bundle packageFromCaller;
	Intent myIntent;
	Bundle bundle;

	int idtopic;
	int idtopicPause;
	String name;

	int score;
	int scorePause;
	String time;
	String timePause;
	
	
	WordDAO word;
	String nametopic;
	Button btnSubmit;
	Button btnAgain;
	Button btnBack;
	EditText etName;
	TextView txtNameTopic;
	TextView txtViewScore;
	TextView txtViewTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		getGIFTPackageIntent();

		initviews();

		Players = new ArrayList<InfoPlayer>();
		loadPlayers = new ArrayList<InfoPlayer>();

		txtViewScore.setText(score + "");
		txtViewTime.setText(time);
		nametopic = getNameTopic(idtopic);
		txtNameTopic.setText(nametopic.toUpperCase() + " SCORES");

		loadData1();

		btnSubmit.setEnabled(false);

		if (etName.getText() == null) {
			btnSubmit.setEnabled(false);

		} else if (etName.getText() != null) {

			btnSubmit.setEnabled(true);
		}

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				solveSubmit();
				loadData2();
				etName.setEnabled(false);
				btnSubmit.setEnabled(false);

			}
		});
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Score.this, MainActivity.class);
				startActivity(intent);
			}
		});
		btnAgain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myIntent = new Intent(Score.this, Fast.class);
				bundle = new Bundle();
				bundle.putInt("idtopic", idtopic);
				myIntent.putExtra("MyPackage", bundle);
				startActivity(myIntent);

			}
		});
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
	private void saveInstant()
	{
		idtopicPause = idtopic;
		scorePause = score;
		timePause  = time;
	}
	private void restoreInstant()
	{
		idtopic=idtopicPause ;
		score= scorePause ;
		time =timePause  ;
	}

	private void initviews() {
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnBack = (Button) findViewById(R.id.btnSlow);
		btnAgain = (Button) findViewById(R.id.btnAgain);
		etName = (EditText) findViewById(R.id.etName);

		txtViewScore = (TextView) findViewById(R.id.txtViewScore);
		txtViewTime = (TextView) findViewById(R.id.txtViewTime);
		lvPlayer = (ListView) findViewById(R.id.lvPlayers);
		txtNameTopic = (TextView) findViewById(R.id.txtNameTopic);
	}

	public void loadData1() {
		scoreDAO = new ScoreDAO(this);
		Players = scoreDAO.getData(idtopic);
		Log.d("TAG1", Players.size() + "");
		adapter = new MyArrayAdapter(this, R.layout.my_item_listview, Players,
				score, time);
		lvPlayer.setAdapter(adapter);
	}

	public void loadData2() {
		scoreDAO = new ScoreDAO(this);
		Players = scoreDAO.getData(idtopic);
		Log.d("TAG2", Players.size() + "");
		adapter = new MyArrayAdapter(this, R.layout.my_item_listview, Players,
				score, time);
		lvPlayer.setAdapter(adapter);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
				&& (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME)
				&& event.getRepeatCount() == 0) {
			onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		saveInstant();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		restoreInstant();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		return;

	}

	private void solveSubmit() {

		scoreDAO = new ScoreDAO(this);
		name = etName.getText().toString().trim();
		scoreDAO.submitToDatabase(idtopic, name, score, time);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
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
