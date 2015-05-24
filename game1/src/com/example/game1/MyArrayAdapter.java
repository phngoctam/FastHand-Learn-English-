package com.example.game1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.IInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<InfoPlayer> {
	Activity context = null;
	ArrayList<InfoPlayer> myArray =null;
	int layoutid;
	int stt ;
	int score;
	String time;
	public MyArrayAdapter(Activity context, int layoutid, ArrayList<InfoPlayer> arr,int score,String time) {
		super(context, layoutid, arr);
		// TODO Auto-generated constructor stub
		this.context= context;
		this.layoutid = layoutid;
		this.myArray = arr;
		this.score = score;
		this.time = time;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutid, null);
		if(myArray.size()>0 && position >=0){
			TextView txtSTT = (TextView) convertView.findViewById(R.id.txtSTT);
			TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
			TextView txtScore = (TextView) convertView.findViewById(R.id.txtScore);
			TextView txtTime = (TextView) convertView.findViewById(R.id.txtTime);
			if(myArray.size() > 0 && position>=0)
			{
				txtSTT.setText(myArray.get(position).getStt()+"");
				txtName.setText(myArray.get(position).get_Name().toString().trim());
				txtScore.setText(myArray.get(position).get_Score()+"");
				txtTime.setText(myArray.get(position).get_Time().toString().trim());
			}
			
		}
		return convertView;
	}
	

}
