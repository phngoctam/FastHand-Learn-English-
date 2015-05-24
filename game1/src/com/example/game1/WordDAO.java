package com.example.game1;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class WordDAO {
	Context _mContext = null;

	public WordDAO(Context context) {
		this._mContext = context;
	}

	public boolean isExist(int _id) {
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		boolean result = false;
		Cursor cur = null;
		try {
			String sql = "Select count(*) from word where _id = '" + _id + "'";
			cur = provider.execQuery(db, sql);
			while (cur.moveToNext()) {
				int i = Integer.valueOf(cur.getString(0));
				if (i != 0)
					result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}
		return result;
	}

	/*
	 * public ArrayList<InfoWORDS> getInfoByIDTOPIC(int idtopic) {
	 * ArrayList<InfoWORDS> arr = new ArrayList<InfoWORDS>();
	 * 
	 * DataProvider provider = new DataProvider(_mContext); SQLiteDatabase db =
	 * provider.openDataBase(); Cursor cur = null; try { String sql =
	 * "Select content from word where _idtopic = '" + idtopic +"'"; cur =
	 * provider.execQuery(db, sql); while (cur.moveToNext()) { InfoWORDS info =
	 * new InfoWORDS();
	 * info.setContent(cur.getString(cur.getColumnIndex("context")));
	 * arr.add(info); } } catch (Exception e) { // TODO: handle exception }
	 * finally{ cur.close(); db.close(); }
	 * 
	 * return arr; }
	 */
	public ArrayList<InfoWORDS> getContentByIDTOPIC(int idtopic) {
		ArrayList<InfoWORDS> arr = new ArrayList<InfoWORDS>();
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		Cursor cur = null;
		try {
			String sql = "Select content from word where _idtopic = '"
					+ idtopic + "'";
			cur = provider.execQuery(db, sql);
			cur.moveToFirst();
			while (cur.isAfterLast() == false) {
				InfoWORDS info = new InfoWORDS();
				info.setContent(cur.getString(cur.getColumnIndex("content")));
			//	Log.d("TAG", "Lay duoc: " + cur.getInt(0));
				arr.add(info);
				cur.moveToNext();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}
		return arr;
	}

	public ArrayList<InfoWORDS> getPictureIDTOPIC(int idtopic) {
		ArrayList<InfoWORDS> arr = new ArrayList<InfoWORDS>();
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		Cursor cur = null;
		try {
			String sql = "Select picture from word where _idtopic = '"
					+ idtopic + "'";
			cur = provider.execQuery(db, sql);
			cur.moveToFirst();
			while (cur.isAfterLast() == false) {
				InfoWORDS info = new InfoWORDS();
				info.setContent(cur.getString(cur.getColumnIndex("content")));
			//	Log.d("TAG", "Lay duoc: " + cur.getInt(0));
				arr.add(info);
				cur.moveToNext();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}
		return arr;
	}

	public String getNameTopicByID(int id) {
		String NameOfTopic = null;
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		Cursor cur = null;
		try { 
			String sql = "select name from topicav where _id ='" + id
					+ "'";
			cur = provider.execQuery(db, sql);
			cur.moveToFirst();
			while (cur.isAfterLast() == false) {
				NameOfTopic = cur.getString(cur.getColumnIndex("name")).toString();
				cur.moveToNext();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}
		return NameOfTopic;
	}

	public ArrayList<InfoWORDS> getNext(int idtopic, int x, int y) {
		ArrayList<InfoWORDS> arrgetNext = new ArrayList<InfoWORDS>();
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		Cursor cur = null;
		try {
			String sql = "select content from word where _idtopic ='" + idtopic
					+ "'" + "limit " + x + "," + y;
			cur = provider.execQuery(db, sql);
			cur.moveToFirst();
			while (cur.isAfterLast() == false) {
				InfoWORDS infogetNext = new InfoWORDS();
				infogetNext.setContent(cur.getString(cur
						.getColumnIndex("content")));
				arrgetNext.add(infogetNext);
				cur.moveToNext();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}
		return arrgetNext;

	}

	public int CountContentByIDTOPICS(int idtopic) {
		int kq = 0;
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		Cursor cur = null;
		try {
			String sql = "Select count(*) from word where _idtopic ='"
					+ idtopic + "'";
			cur = provider.execQuery(db, sql);
			cur.moveToFirst();
			while (cur.isAfterLast() == false) {
				kq = cur.getInt(cur.getColumnIndex("count(*)"));
				cur.moveToNext();
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cur.close();
			db.close();
		}

		return kq;
	}
	public int insertWordToDatabase(int midtopic,String mContent,String mPicture,String mSound)
	{
		int result=0;
		DataProvider provider = new DataProvider(_mContext);
		SQLiteDatabase db = provider.openDataBase();
		try{
			ContentValues cv = new ContentValues();
			cv.put("_idtopic",midtopic);
			cv.put("content", mContent);
			cv.put("picture", mPicture);
			cv.put("sound",mSound);
			result= provider.execInsertCommand(db,"word", cv);
			System.out.println("kq: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			db.close();
		}
		return result;
	}

}
