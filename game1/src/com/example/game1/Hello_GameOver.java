package com.example.game1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Hello_GameOver extends Activity {

	Intent myIntent;
	Bundle bundle;
	Intent callerIntent;
	Bundle packageFromCaller;
	
	int score;
	String time;
	int giftIdtopic;
	int idtopic;
	int kqscore;
	String kqtime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello__game_over);
		getGIFTPackageIntent();
		Thread bamgio = new Thread()
		{
			
			public void run(){
				try {
					sleep(3000);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					myIntent = new Intent(Hello_GameOver.this, Score.class);
					bundle = new Bundle();
					giftIdtopic = idtopic;
					bundle.putInt("idtopic", giftIdtopic);
					bundle.putInt("kqscore", score);
					bundle.putString("kqtime", time);
					myIntent.putExtra("MyPackage", bundle);
					startActivity(myIntent);	
				}
			}
		
		};
		bamgio.start();
		
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

	private void getGIFTPackageIntent() {
		// Get Gift from score hihi
		callerIntent = getIntent();
		packageFromCaller = callerIntent.getBundleExtra("MyPackage");
		idtopic = packageFromCaller.getInt("idtopic");
		score = packageFromCaller.getInt("kqscore");
		time = packageFromCaller.getString("kqtime");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello__game_over, menu);
		return true;
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
