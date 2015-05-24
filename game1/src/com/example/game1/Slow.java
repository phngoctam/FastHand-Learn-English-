package com.example.game1;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.example.game1.Fast.MyCounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Slow extends Activity {

	ProgressBar mProgressBar;
	TextView mTextScore;
	TextView mTextCauhoi;
	TextView mTextTime;
	TextView mTextSoCau;
	GridView gv;
	MediaPlayer mpsound;
	Button btnBack;
	Button btn_Pause;
	CountDownTimer mCountDownTimer;
	long millisInFutureaa;
	long countDownIntervalaa;

	final DecimalFormat df = new DecimalFormat("0.00");
	float flag;
	float flag2;
	int flagPause =0;

	int score = 0;
	int socau = 0;
	int kqscore;
	String kqtime;

	long level1;
	long level2;
	long level3;
	long level4;

	MyCounter timer;
	MyCounter timer2;
	boolean isRunningTime = false;
	boolean isRunningTime2 = false;

	Handler mHandler = new Handler();
	long mStartTime = 0L;

	Intent myIntent;
	Bundle bundle;
	Intent callerIntent;
	Bundle packageFromCaller;
	SQLiteDatabase db;
	DataProvider provider;
	WordDAO word;
	String nametopic;
	int idtopic;
	int giftIdtopic;
	int x, y;
	int kq;
	int kqtext = 0;
	ArrayList<String> alltext;
	ArrayList<String> alltext2;

	MyImageAdapter adapter = null;

	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	long remainMilli = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slow);

		// Get Gift from menufast hihi
		callerIntent = getIntent();
		packageFromCaller = callerIntent.getBundleExtra("MyPackage");
		idtopic = packageFromCaller.getInt("idtopic");
		// Create database
		provider = new DataProvider(this);
		provider.checkAndCopyDatabase();
		provider.openDataBase();

		initviews();
		alltext = new ArrayList<String>();
		millisInFutureaa = 16 * 1000; // 10 seconds in milli seconds
		countDownIntervalaa = 100;

		word = new WordDAO(this);
		nametopic = getNameTopic(idtopic);
		alltext2 = loadgetNext(idtopic, 0, 10);
		kq = CountAllContent(idtopic);
		kqtext = alltext.size();

		level1 = millisInFutureaa - 1000;// 11
		level2 = millisInFutureaa - 2000;// 10
		level3 = millisInFutureaa - 3000;// 9
		level4 = millisInFutureaa - 4000;// 8
		final MyCounter timer = new MyCounter(millisInFutureaa,
				countDownIntervalaa);
		timer.start();
		final Random r = new Random();

		// thiết lập Datasource cho Adapter
		adapter = new MyImageAdapter(Slow.this, alltext, idtopic);
		// gán Adapter vào Gridview
		gv.setAdapter(adapter);
		mTextCauhoi.setText(alltext.get(0).toString());
		Sound();

		if (mStartTime == 0L) {
			mStartTime = SystemClock.uptimeMillis();
			mHandler.removeCallbacks(mUpdateTime);
			mHandler.postDelayed(mUpdateTime, 100);

		}

		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				if (alltext.get(position).toString() == mTextCauhoi.getText()
						.toString()) {
					if (kqtext < kq) {
						alltext = loadgetNext(idtopic, x += 10, 10);
						alltext2 = alltext;
						kqtext = alltext2.size();
					}
					Collections.shuffle(alltext2);
					adapter = new MyImageAdapter(Slow.this, alltext2, idtopic);
					gv.setAdapter(adapter);
					mTextCauhoi.setText(alltext2.get(r.nextInt(10) + 0));
					Sound();
					timer.cancel();
					socau++;
					mTextSoCau.setText(socau + "");
					doStart(millisInFutureaa, countDownIntervalaa);
				} else {
					view.setVisibility(View.GONE);
				}

			}

		});
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isRunningTime == true) {
					Slow.this.timer.cancel();
					mHandler.removeCallbacks(mUpdateTime);
				}
				if (isRunningTime2 == true) {

					timer2.cancel();
				//	Toast.makeText(getApplicationContext(),
				//			isRunningTime2 + " time2", Toast.LENGTH_SHORT)
				//			.show();
					mHandler.removeCallbacks(mUpdateTime);
				}

				timer.cancel();
				Intent intent = new Intent(Slow.this, MainActivity.class);
				startActivity(intent);
				finish();

			}
		});
		btn_Pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isRunningTime == true) {
					Slow.this.timer.cancel();
					isRunningTime = false;
					// Toast.makeText(getApplicationContext(),
					// "Here1",Toast.LENGTH_SHORT).show();
				}
				if (isRunningTime2 == true) {
					timer2.cancel();
					isRunningTime2 = false;
				}
				timer.cancel();
				timeSwapBuff += timeInMilliseconds;
				mHandler.removeCallbacks(mUpdateTime);
				// Toast.makeText(getApplicationContext(),
				// "pause",Toast.LENGTH_SHORT).show();
				AlertDialog myAlertDialog = taoMotAlertDialog();
				myAlertDialog.show();

			}
		});
	}
	  private AlertDialog taoMotAlertDialog(){
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	   
	        //Thiết lập thông báo hiển thị
	        builder.setMessage("\t\t\t\t\t\tGAME IS PAUSED!");
	        builder.setCancelable(false);
	        builder.setPositiveButton("RESUME",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int which) {
	                    	if (isRunningTime== false) {
	        					Slow.this.timer = new MyCounter(remainMilli, 100);
	        					Slow.this.timer.start();
	        					isRunningTime = true;
	        				}
	        				if (isRunningTime2 == false) {
	        					timer2 = new MyCounter(remainMilli, 100);
	        					timer2.start();
	        					isRunningTime2 = true;
	        				}
	        				 mStartTime = SystemClock.uptimeMillis();
	        				 mHandler.postDelayed(mUpdateTime, 80);

	                    }
	                });
	        AlertDialog dialog = builder.create();
	        return dialog;
	    }
	private void initviews() {
		btn_Pause = (Button) findViewById(R.id.btn_Pause);
		btnBack = (Button) findViewById(R.id.btnBack);
		gv = (GridView) findViewById(R.id.gridView1);
		mTextSoCau = (TextView) findViewById(R.id.txtSoCau);
		mTextTime = (TextView) findViewById(R.id.txtTime);
		mTextScore = (TextView) findViewById(R.id.txtScore);
		mTextCauhoi = (TextView) findViewById(R.id.txtQuestion);
		mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

	}

	public void sendResulScoreWin() {

		myIntent = new Intent(Slow.this, Hello_Victory.class);
		bundle = new Bundle();
		giftIdtopic = idtopic;
		kqscore = Integer.parseInt(mTextScore.getText().toString().trim());
		Log.d("TAGkqscore1", kqscore + "");
		kqtime = mTextTime.getText().toString().trim();
		bundle.putInt("idtopic", giftIdtopic);
		bundle.putInt("kqscore", kqscore);
		bundle.putString("kqtime", kqtime);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);
	}

	public void sendResulScore() {

		myIntent = new Intent(Slow.this, Hello_GameOver.class);
		bundle = new Bundle();
		giftIdtopic = idtopic;
		kqscore = Integer.parseInt(mTextScore.getText().toString().trim());
		Log.d("TAGkqscore1", kqscore + "");
		kqtime = mTextTime.getText().toString().trim();
		bundle.putInt("idtopic", giftIdtopic);
		bundle.putInt("kqscore", kqscore);
		bundle.putString("kqtime", kqtime);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void sendResulScoreZero() {

		myIntent = new Intent(Slow.this, Hello_GameOver_Zero.class);
		bundle = new Bundle();
		giftIdtopic = idtopic;
		kqscore = Integer.parseInt(mTextScore.getText().toString().trim());
		Log.d("TAGkqscore1", kqscore + "");
		kqtime = mTextTime.getText().toString().trim();
		bundle.putInt("idtopic", giftIdtopic);
		bundle.putInt("kqscore", kqscore);
		bundle.putString("kqtime", kqtime);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void doStart(long a, long b) {

		if (isRunningTime == true) {
			timer.cancel();
			isRunningTime = false;
		}
		if (isRunningTime2 == true) {
			timer2.cancel();
			isRunningTime2 = false;
		}

		if (mProgressBar.getProgress() >= 50.00) {
			if (socau >= 0 && socau <= 19) {
				timer = new MyCounter(a, b);
				timer.start();
				isRunningTime = true;
				score += 50;
				mTextScore.setText(score + "");
			} else if (socau >= 20 && socau <= 39) {
				timer = new MyCounter(level1, b);
				timer.start();
				isRunningTime = true;
				score += 75;
				mTextScore.setText(score + "");
			} else if (socau >= 40 && socau <= 59) {
				timer = new MyCounter(level2, b);
				timer.start();
				isRunningTime = true;
				score += 100;
				mTextScore.setText(score + "");
			} else if (socau >= 60 && socau <= 69) {
				timer = new MyCounter(level3, b);
				timer.start();
				isRunningTime = true;
				score += 125;
				mTextScore.setText(score + "");
			} else if (socau >= 70 && socau <= 79) {
				timer = new MyCounter(level4, b);
				timer.start();
				isRunningTime = true;
				score += 150;
				mTextScore.setText(score + "");
			}

		} else {
			if (socau >= 0 && socau <= 19) {
				mProgressBar.setProgress(mProgressBar.getProgress() + 30);
				flag = (float) ((mProgressBar.getProgress() + 30) / 100.0);
				timer2 = new MyCounter((long) (millisInFutureaa * flag), b);
				timer2.start();
				score += 35;
				mTextScore.setText(score + "");
				isRunningTime2 = true;
			} else if (socau >= 20 && socau <= 39) {
				millisInFutureaa = level1;
				mProgressBar.setProgress(mProgressBar.getProgress() + 30);
				flag = (float) ((mProgressBar.getProgress() + 30) / 100.0);
				timer2 = new MyCounter((long) ((level1 * flag) - 1000), b);
				timer2.start();
				score += 53;
				mTextScore.setText(score + "");
				isRunningTime2 = true;
			} else if (socau >= 40 && socau <= 59) {
				millisInFutureaa = level2;
				mProgressBar.setProgress(mProgressBar.getProgress() + 30);
				flag = (float) ((mProgressBar.getProgress() + 30) / 100.0);
				timer2 = new MyCounter((long) ((level2 * flag) - 1000), b);
				timer2.start();
				score += 70;
				mTextScore.setText(score + "");
				isRunningTime2 = true;
			} else if (socau >= 60 && socau <= 69) {
				millisInFutureaa = level3;
				mProgressBar.setProgress(mProgressBar.getProgress() + 20);
				flag = (float) ((mProgressBar.getProgress() + 20) / 100.0);
				timer2 = new MyCounter((long) ((level3 * flag) - 1000), b);
				timer2.start();
				score += 88;
				mTextScore.setText(score + "");
				isRunningTime2 = true;
			} else if (socau >= 70 && socau <= 79) {
				millisInFutureaa = level4;
				mProgressBar.setProgress(mProgressBar.getProgress() + 10);
				flag = (float) ((mProgressBar.getProgress() + 10) / 100.0);
				timer2 = new MyCounter((long) ((level4 * flag) - 1000), b);
				timer2.start();
				score += 105;
				mTextScore.setText(score + "");
				isRunningTime2 = true;
			}
		}
		if (socau == 80) {
			if (isRunningTime == true) {
				timer.cancel();
				isRunningTime = false;
			}
			if (isRunningTime2 == true) {
				timer2.cancel();
				isRunningTime2 = false;
			}
			mHandler.removeCallbacks(mUpdateTime);
			Toast.makeText(getApplicationContext(), "Victory",
					Toast.LENGTH_LONG).show();
			sendResulScoreWin();
		}

	}

	public class MyCounter extends CountDownTimer {

		public MyCounter(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
			millisInFuture = millisInFutureaa;
			countDownInterval = countDownIntervalaa;

		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			remainMilli = millisUntilFinished;
			float setprocess = millisUntilFinished / (float) millisInFutureaa;
			float settext = (float) ((float) (millisInFutureaa - millisUntilFinished) / 1000.0);
			mProgressBar.setProgress((int) (setprocess * 100));
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub

			mProgressBar.setProgress(0);

			mHandler.removeCallbacks(mUpdateTime);

			if (socau == 0) {
				sendResulScoreZero();

			} else if (socau <= 79) {
				sendResulScore();
				Log.d("TAGSOCAU", "da vao ham onfinish" + socau);
				;
			}

		}

	}

	public String getNameTopic(int idtopic) {
		word = new WordDAO(this);
		String nameTopic = word.getNameTopicByID(idtopic);
		return nameTopic;
	}

	public void Sound() {
		mpsound = new MediaPlayer();
		try {
			AssetFileDescriptor descriptor = getAssets().openFd(
					nametopic.toString() + "/" + "sound/"
							+ mTextCauhoi.getText().toString() + ".mp3");
			mpsound.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(), descriptor.getLength());
			descriptor.close();
			mpsound.prepare();
			mpsound.start();
			mpsound.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				};
			});

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getQuestion(int idtopic) {
		word = new WordDAO(this);
		ArrayList<InfoWORDS> ifw = word.getContentByIDTOPIC(idtopic);
		for (int i = 0; i < ifw.size(); i++) {
			String tmp = ifw.get(i).getContent();
			alltext.add(tmp);
		}
		return alltext;

	}

	public int CountAllContent(int idtopic) {
		word = new WordDAO(this);
		int kq = word.CountContentByIDTOPICS(idtopic);
		return kq;
	}

	public ArrayList<String> loadgetNext(int idtopic, int x, int y) {
		word = new WordDAO(this);
		ArrayList<InfoWORDS> ifw = word.getNext(idtopic, x, y);
		for (int i = 0; i < ifw.size(); i++) {
			String tmp = ifw.get(i).getContent();
			alltext.add(tmp);
		}
		return alltext;

	}

	private Runnable mUpdateTime = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			long start = mStartTime;
			long millis = SystemClock.uptimeMillis() - start;
			float seconds = (float) (millis / 1000.0);
			mTextTime.setText(df.format((float) seconds));
			mHandler.postDelayed(this, 200);
		}
	};

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
		if(flagPause == 0)
		{	
			if (isRunningTime== true) {
				Slow.this.timer.cancel();
				isRunningTime = false;
				//Toast.makeText(getApplicationContext(), "Here1",Toast.LENGTH_SHORT).show();
			}
			if (isRunningTime2 == true) {
				timer2.cancel();	
				isRunningTime2 = false;
			}	
			timer.cancel();
			timeSwapBuff += timeInMilliseconds;
			mHandler.removeCallbacks(mUpdateTime);
			//Toast.makeText(getApplicationContext(), "pause",Toast.LENGTH_SHORT).show();
			 AlertDialog myAlertDialog = taoMotAlertDialog();
	         myAlertDialog.show();
		}
		else
			flagPause--;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		return;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fast, menu);
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
