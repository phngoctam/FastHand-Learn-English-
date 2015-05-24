package com.example.game1;

import com.example.game1.MenuReview.MyEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MenuReview extends Activity {
	ImageView btnanimal1;
	ImageView btncolor;
	ImageView btnbody1;

	ImageView btnpersonal;
	ImageView btnfood1;
	ImageView btnclothes1;
	ImageView btnleters;
	ImageView btnfurniture;
	ImageView btnkitchen;
	ImageView btnfood2;
	ImageView btnanimal2;
	ImageView btnbody2;
	ImageView btnclothes2;
	ImageView btnnumbers1;
	ImageView btnsports;
	ImageView btntransport;
	ImageView btnjobs;
	ImageView btnstationery;
	ImageView btnverbs1;
	ImageView btnadjectives;
	ImageView btncomputer;
	ImageView btntraffic;
	ImageView btngarden;
	ImageView btnhospital;
	ImageView btnnature;
	ImageView btnpositions;
	int idtopic;
	Intent myIntent;
	Bundle bundle;

	private ViewFlipper viewFlipper;
	private float lastX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_review);

		viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

		btnanimal1 = (ImageView) findViewById(R.id.btnanimals1);
		btnanimal1.setOnClickListener(new MyEvent());
		btncolor = (ImageView) findViewById(R.id.btncolor);
		btncolor.setOnClickListener(new MyEvent());
		btnbody1 = (ImageView) findViewById(R.id.btnbody1);
		btnbody1.setOnClickListener(new MyEvent());

		btnpersonal = (ImageView) findViewById(R.id.btnpersonal);
		btnfood1 = (ImageView) findViewById(R.id.btnfood1);
		btnclothes1 = (ImageView) findViewById(R.id.btnclothes1);
		btnleters = (ImageView) findViewById(R.id.btnletters);
		btnfurniture = (ImageView) findViewById(R.id.btnfurniture);
		btnkitchen = (ImageView) findViewById(R.id.btnkitchen);
		btnfood2 = (ImageView) findViewById(R.id.btnfood2);
		btnanimal2 = (ImageView) findViewById(R.id.btnanimals2);
		btnbody2 = (ImageView) findViewById(R.id.btnbody2);
		btnclothes2 = (ImageView) findViewById(R.id.btnclothes2);
		btnnumbers1 = (ImageView) findViewById(R.id.btnnumbers1);
		btnsports = (ImageView) findViewById(R.id.btnsports);
		btntransport = (ImageView) findViewById(R.id.btntransport);
		btnjobs = (ImageView) findViewById(R.id.btnjobs);
		btnstationery = (ImageView) findViewById(R.id.btnstationery);
		btnverbs1 = (ImageView) findViewById(R.id.btnverbs1);
		btnadjectives = (ImageView) findViewById(R.id.btnadjectives);
		btncomputer = (ImageView) findViewById(R.id.btncomputer);
		btntraffic = (ImageView) findViewById(R.id.btntraffic);
		btngarden = (ImageView) findViewById(R.id.btngarden);
		btnhospital = (ImageView) findViewById(R.id.btnhospital);
		btnnature = (ImageView) findViewById(R.id.btnnature);
		btnpositions = (ImageView) findViewById(R.id.btnpositions);

		btnpersonal.setOnClickListener(new MyEvent());
		;
		btnfood1.setOnClickListener(new MyEvent());
		;
		btnclothes1.setOnClickListener(new MyEvent());
		;
		btnleters.setOnClickListener(new MyEvent());
		;
		btnfurniture.setOnClickListener(new MyEvent());
		;
		btnkitchen.setOnClickListener(new MyEvent());
		;
		btnfood2.setOnClickListener(new MyEvent());
		;
		btnanimal2.setOnClickListener(new MyEvent());
		;
		btnbody2.setOnClickListener(new MyEvent());
		;
		btnclothes2.setOnClickListener(new MyEvent());
		;
		btnnumbers1.setOnClickListener(new MyEvent());
		;
		btnsports.setOnClickListener(new MyEvent());
		;
		btntransport.setOnClickListener(new MyEvent());
		;
		btnjobs.setOnClickListener(new MyEvent());
		;
		btnstationery.setOnClickListener(new MyEvent());
		;
		btnverbs1.setOnClickListener(new MyEvent());
		;
		btnadjectives.setOnClickListener(new MyEvent());
		;
		btncomputer.setOnClickListener(new MyEvent());
		;
		btntraffic.setOnClickListener(new MyEvent());
		;
		btngarden.setOnClickListener(new MyEvent());
		;
		btnhospital.setOnClickListener(new MyEvent());
		;
		btnnature.setOnClickListener(new MyEvent());
		;
		btnpositions.setOnClickListener(new MyEvent());
		;
	}

	public void solvetopic1() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 1);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	// Method to handle touch event like left to right swap and right to left
	// swap

	public void solvetopic3() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 3);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic4() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 4);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic2() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 2);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic5() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 5);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic6() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 6);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic7() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 7);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic8() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 8);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic9() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 9);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic10() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 10);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic11() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 11);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic12() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 12);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic13() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 13);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic14() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 14);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic15() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 15);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic16() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 16);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic17() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 17);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic18() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 18);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic19() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 19);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic20() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 20);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic21() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 21);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic22() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 22);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic23() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 23);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic24() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 24);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic25() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 25);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public void solvetopic26() {
		myIntent = new Intent(MenuReview.this, Review.class);
		bundle = new Bundle();
		bundle.putInt("idtopic", 26);
		myIntent.putExtra("MyPackage", bundle);
		startActivity(myIntent);

	}

	public boolean onTouchEvent(MotionEvent touchevent) {
		switch (touchevent.getAction()) {
		// when user first touches the screen to swap
		case MotionEvent.ACTION_DOWN: {
			lastX = touchevent.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float currentX = touchevent.getX();

			// if left to right swipe on screen
			if (lastX < currentX) {
				// If no more View/Child to flip
				if (viewFlipper.getDisplayedChild() == 0)
					break;

				// set the required Animation type to ViewFlipper
				// The Next screen will come in form Left and current Sceen
				// will go OUT from Right
				viewFlipper.setInAnimation(this, R.anim.in_from_left);
				viewFlipper.setOutAnimation(this, R.anim.out_to_right);
				// Show the next Screen
				viewFlipper.showNext();
			}

			// if right to left swipe on screen
			if (lastX > currentX) {
				if (viewFlipper.getDisplayedChild() == 1)
					break;
				// set the required Animation type to ViewFlipper
				// The Next screen will come in form Right and current Screen
				// will go OUT from Left
				viewFlipper.setInAnimation(this, R.anim.in_from_right);
				viewFlipper.setOutAnimation(this, R.anim.out_to_left);
				// Show The Previous Screen
				viewFlipper.showPrevious();
			}
			break;
		}
		}
		return false;
	}

	public class MyEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.btnanimals1) {
				solvetopic1();
			} else if (v.getId() == R.id.btncolor) {
				solvetopic3();
			} else if (v.getId() == R.id.btnbody1) {
				solvetopic4();
			} else if (v.getId() == R.id.btnpersonal) {
				solvetopic20();
			}

			else if (v.getId() == R.id.btnfood1) {
				solvetopic10();
			} else if (v.getId() == R.id.btnclothes1) {
				solvetopic7();
			} else if (v.getId() == R.id.btnletters) {
				solvetopic17();
			} else if (v.getId() == R.id.btnfurniture) {
				solvetopic12();
			} else if (v.getId() == R.id.btnkitchen) {
				solvetopic16();
			} else if (v.getId() == R.id.btnfood2) {
				solvetopic11();
			} else if (v.getId() == R.id.btnanimals2) {
				solvetopic2();
			} else if (v.getId() == R.id.btnbody2) {
				solvetopic6();
			} else if (v.getId() == R.id.btnclothes2) {
				solvetopic8();
			} else if (v.getId() == R.id.btnnumbers1) {
				solvetopic19();
			} else if (v.getId() == R.id.btnsports) {
				solvetopic22();
			} else if (v.getId() == R.id.btntransport) {
				solvetopic25();
			} else if (v.getId() == R.id.btnjobs) {
				solvetopic15();
			} else if (v.getId() == R.id.btnverbs1) {
				solvetopic26();
			} else if (v.getId() == R.id.btnstationery) {
				solvetopic23();
			} else if (v.getId() == R.id.btnadjectives) {
				solvetopic5();
			} else if (v.getId() == R.id.btncomputer) {
				solvetopic9();
			} else if (v.getId() == R.id.btntraffic) {
				solvetopic24();
			} else if (v.getId() == R.id.btngarden) {
				solvetopic13();
			} else if (v.getId() == R.id.btnhospital) {
				solvetopic14();
			} else if (v.getId() == R.id.btnnature) {
				solvetopic18();
			} else if (v.getId() == R.id.btnpositions) {
				solvetopic21();
			}

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myintent = new Intent(MenuReview.this, MainActivity.class);
		startActivity(myintent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_review, menu);
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
