package com.example.game1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyImageAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> mThumbIds;
	 private LayoutInflater inflater;
	private int midtopic;
	WordDAO word;
	String nametopic;

	public MyImageAdapter(Context c, int idtopic) {
		mContext = c;
		midtopic = idtopic;
	}

	public MyImageAdapter(Context c, ArrayList<String> arrIds, int idtopic) {
		mContext = c;
		inflater = LayoutInflater.from(c);
		mThumbIds = arrIds;
		midtopic = idtopic;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// return mThumbIds.size();
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNameTopic(int idtopic) {
		word = new WordDAO(mContext);
		String nameTopic = word.getNameTopicByID(idtopic);
		return nameTopic;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 View v = convertView;
	     ImageView picture;
		Animation animationFadeIn = AnimationUtils.loadAnimation(this.mContext,
				R.anim.fadein);
		Animation animationFadeOut = AnimationUtils.loadAnimation(
				this.mContext, R.anim.fadeout);
		AssetManager assetManager = mContext.getAssets();
		InputStream ims;
		//ImageView imgView = new ImageView(mContext);
		Drawable d = null;
		nametopic = getNameTopic(midtopic);
		try {
			ims = assetManager.open(nametopic.toString() + "/"
					+ mThumbIds.get(position).toString() + ".jpg");
			Log.d("TAG", nametopic.toString());
			d = Drawable.createFromStream(ims, null);
			
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(v == null){
			
			  v = inflater.inflate(R.layout.grid_item, parent, false);
			  v.setTag(R.id.picture, v.findViewById(R.id.picture));
			// imgView.setAnimation(null);

		} 
		 picture = (ImageView)v.getTag(R.id.picture);
		 picture.startAnimation(animationFadeIn);
		 picture.setImageDrawable(d);
		return v;
	}

}