package com.example.game1;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ScoreDAO {
	Context _mContext = null;
	int stt =0;
	public ScoreDAO(Context context)
	{
		this._mContext = context;
	}

	public int submitToDatabase(int midtopic,String mName,int mScore,String mTime)
	{
		int result=0;
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		try{
			ContentValues cv = new ContentValues();
			cv.put("_idtopic",midtopic);
			cv.put("name", mName);
			cv.put("score", mScore);
			cv.put("time",mTime);
			result= provider.execInsertCommand(db,"score", cv);
			System.out.println("kq: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			db.close();
		}
		return result;
	}
	public ArrayList<InfoPlayer> getData(int idtopic) {
		ArrayList<InfoPlayer> array = new ArrayList<InfoPlayer>();
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		Cursor cur = null;
		try {
			String sql = "select name,score,time from score where _idtopic= '" + idtopic + "'" +"order by score desc limit 50";
			cur = provider.execQuery(db, sql);
			cur.moveToFirst();
			while (cur.isAfterLast() == false) {
				stt +=1;
				InfoPlayer info = new InfoPlayer();
				info.setStt(stt);
				info.set_Name(cur.getString(cur.getColumnIndex("name")));
				info.set_Score(cur.getInt(cur.getColumnIndex("score")));
				info.set_Time(cur.getString(cur.getColumnIndex("time")));
				Log.d("TAG", "Lay duoc player: " + cur.getInt(0));
				array.add(info);		
				cur.moveToNext();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}
		return array;
	}
}
