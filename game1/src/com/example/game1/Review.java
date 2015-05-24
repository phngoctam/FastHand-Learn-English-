package com.example.game1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class Review extends Activity {
	// solve getNext
	Button btnback;
	WordDAO word;
	SQLiteDatabase db;
	DataProvider provider;
	ArrayList<String> textToShow;
	int idtopic;
	int x, y;

	int kq;
	int kqtext = 0;
	// khi bao bien
	MediaPlayer mpsound;
	Button btnNext;
	ImageView btnSound;
	Button btnPrevious;
	ImageView btnBack;
	String nametopic;
	Intent callerIntent;
	Bundle packageFromCaller;
	private ImageSwitcher imageSwitcher;
	private TextSwitcher mSwitcher;
	// to keep current Index of ImageID array
	// maybe call is flag to solve button next or previous
	int currentIndex = 0;
	int currentsound;
	int currentprevioustext = currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		// Create database
		provider = new DataProvider(this);
		provider.checkAndCopyDatabase();
		provider.openDataBase();

		// Get Gift from menureview hihi
		callerIntent = getIntent();
		packageFromCaller = callerIntent.getBundleExtra("MyPackage");
		idtopic = packageFromCaller.getInt("idtopic");

		textToShow = new ArrayList<String>();

		// get name of topic
		nametopic = getNameTopic(idtopic);
		Log.d("Tagnametopic", nametopic);
		// load du lieu vao text to show
		loadgetNext(idtopic, 0, 2);
		// field kq is total record in topic
		kq = CountAllContent(idtopic);
		// field kq is total element in array
		kqtext = textToShow.size();
		// display bacground
		displayTextAndImage();

		btnNext = (Button) findViewById(R.id.buttonNext);
		btnNext.setOnClickListener(new MyEvent());
		btnPrevious = (Button) findViewById(R.id.buttonPrevious);
		if (currentIndex == 0 && currentprevioustext == 0) {
			btnPrevious.setEnabled(false);
			btnPrevious.setClickable(false);
		}
		btnPrevious.setOnClickListener(new MyEvent());
		btnSound = (ImageView) findViewById(R.id.buttonSound);
		btnSound.setOnClickListener(new MyEvent());
		btnBack = (ImageView) findViewById(R.id.buttonBACK);
		btnBack.setOnClickListener(new MyEvent());

	}

	/* 1)method count all content in topic */
	public int CountAllContent(int idtopic) {
		word = new WordDAO(this);
		int kq = word.CountContentByIDTOPICS(idtopic);
		return kq;
	}

	/* 2)solve animation of image swicher */
	public void AnimationimageSwicher() {
		// animation
		final Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		final Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		// ********************solve imageSwitcher **************//
		// *******************************************************//
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		// Set the ViewFactory of the ImageSwitcher that will create ImageView
		// object when asked
		imageSwitcher.setFactory(new ViewFactory() {

			public View makeView() {
				// TODO Auto-generated method stub

				// Create a new ImageView set it's properties
				ImageView imageView = new ImageView(getApplicationContext());
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return imageView;
			}
		});
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);
	}

	/* 3)solve animation of text */
	public void AnimationmSwitcher() {
		// animation
		final Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		final Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		// ************* solve mSwitcher***************//
		mSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);

		// Set the ViewFactory of the TextSwitcher that will create TextView
		// object when asked
		mSwitcher.setFactory(new ViewFactory() {

			public View makeView() {
				// TODO Auto-generated method stub
				// create new textView and set the properties like clolr, size
				// etc
				TextView myText = new TextView(Review.this);
				myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
				myText.setTextSize(36);
				myText.setTextColor(Color.BLUE);
				return myText;
			}
		});
		// set the animation type of textSwitcher
		mSwitcher.setInAnimation(in);
		mSwitcher.setOutAnimation(out);

	}

	/* 4) solve button next */
	public void Next() {
		btnPrevious.setClickable(true);
		btnPrevious.setEnabled(true);
		currentIndex = currentIndex + 1;
		currentsound = currentIndex;
		if (currentIndex == kq - 1) {
			currentIndex = kq - 1;
			btnNext.setClickable(false);
			btnNext.setEnabled(false);
		} else if (currentIndex == 0) {
			currentIndex = 0;
		}

		if (kqtext < kq && kqtext == currentIndex) {
			loadgetNext(idtopic, x += 2, 2);
			kqtext = textToShow.size();
			Log.d("TAGkqtext", kqtext + "");
		}
		displayTextAndImage2();
	}

	/* 5) solve button previous */
	public void Previous() {
		btnNext.setClickable(true);
		btnNext.setEnabled(true);
		currentprevioustext = currentIndex - 1;
		currentIndex = currentprevioustext;
		currentsound = currentprevioustext;
		Log.d("TAGprevious", currentprevioustext + "");
		if (currentprevioustext <= 0) {
			btnPrevious.setClickable(false);
			btnPrevious.setEnabled(false);
			currentprevioustext = 0;
			currentIndex = 0;
			currentsound = 0;

		}

		displayTextAndImagePrevious();

	}

	/* 6) solve button sound and mediaplayer */
	public void Sound() {
		CommonMethod.player.stop();
		mpsound = new MediaPlayer();
		try {
			AssetFileDescriptor descriptor = getAssets().openFd(
					nametopic.toString() + "/" + "sound/"
							+ textToShow.get(currentsound).toString() + ".mp3");
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

	/*
	 * 7) solve get data from asset folder... to THE FIRST display text and
	 * image animation
	 */
	/*
	 * phai co ham nay boi vi o tren da tao display roi bay gio hien thi cai dau
	 * tien
	 */
	public void displayTextAndImage() {
		AssetManager assetManager = getAssets();
		InputStream ims;
		try {
			ims = assetManager.open(nametopic.toString() + "/"
					+ textToShow.get(0).toString() + ".jpg");
			Drawable d = Drawable.createFromStream(ims, null);
			AnimationimageSwicher();
			imageSwitcher.setImageDrawable(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnimationmSwitcher();
		mSwitcher.setText(textToShow.get(0).toString());
	}

	/*
	 * 8) solve get data from asset folder... to display the next text and image
	 * animation
	 */
	public void displayTextAndImage2() {
		AssetManager assetManager = getAssets();
		final Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		final Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);
		mSwitcher.setInAnimation(in);
		mSwitcher.setOutAnimation(out);
		InputStream ims;
		try {
			ims = assetManager.open(nametopic.toString() + "/"
					+ textToShow.get(currentIndex).toString() + ".jpg");
			Drawable d = Drawable.createFromStream(ims, null);
			imageSwitcher.setImageDrawable(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mSwitcher.setText(textToShow.get(currentIndex).toString());
	}

	/* 9) solve display text and image previuos */
	public void displayTextAndImagePrevious() {
		AssetManager assetManager = getAssets();
		final Animation preIn = AnimationUtils.loadAnimation(this,
				R.anim.slide_in_right);
		final Animation preOut = AnimationUtils.loadAnimation(this,
				R.anim.slide_out_left);
		imageSwitcher.setInAnimation(preIn);
		imageSwitcher.setOutAnimation(preOut);
		mSwitcher.setInAnimation(preIn);
		mSwitcher.setOutAnimation(preOut);
		InputStream ims;
		try {
			ims = assetManager.open(nametopic.toString() + "/"
					+ textToShow.get(currentIndex).toString() + ".jpg");
			Drawable d = Drawable.createFromStream(ims, null);
			imageSwitcher.setImageDrawable(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mSwitcher.setText(textToShow.get(currentIndex).toString());
	}

	/* 10)method is load each it each 2 element */
	public ArrayList<String> loadgetNext(int idtopic, int x, int y) {

		word = new WordDAO(this);
		ArrayList<InfoWORDS> ifw = word.getNext(idtopic, x, y);
		// ListView lv = (ListView) findViewById(R.id.listView1);

		for (int i = 0; i < ifw.size(); i++) {
			String tmp = ifw.get(i).getContent();
			textToShow.add(tmp);
			// Log.d("TAGtext", textToShow.get(i).toString() + "");
		}

		return textToShow;
	}

	/* 11)method get name of topic */
	public String getNameTopic(int idtopic) {
		word = new WordDAO(this);
		String nameTopic = word.getNameTopicByID(idtopic);
		return nameTopic;
	}

	/* 12)solve button back */
	public void Back() {
		Intent myintent = new Intent(Review.this, MenuReview.class);
		startActivity(myintent);
	}

	/* 13) finish activity context.this */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myintent = new Intent(Review.this, MenuReview.class);
		startActivity(myintent);
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

	public class MyEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.buttonNext) {

				Next();
			} else if (v.getId() == R.id.buttonPrevious) {
				Previous();
			} else if (v.getId() == R.id.buttonSound) {
				Sound();
			} else if (v.getId() == R.id.buttonBACK) {
				Back();
			}
		}

	}
}
