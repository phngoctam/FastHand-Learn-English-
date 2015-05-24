package com.example.game1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class DataProvider extends SQLiteOpenHelper {
	private static String DB_PATH = "/data/data/com.example.game1/databases/";
	private static String DB_NAME = "testdb.s3db";
	private String TAG = "TAG";
	private SQLiteDatabase mDataBase;
	private final Context mContext;

	public DataProvider(Context context) {
		super(context, DB_NAME, null, 1);
		this.mContext = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	public void checkAndCopyDatabase(){
		boolean dbExist = checkDataBase();
    	if(dbExist){
    		Log.d(TAG, "database already exist!");
    	}else{
        	this.getReadableDatabase();
        	try {
    			copyDataBase();
    		} catch (IOException e) {
    			Log.d(TAG,"Error copying database");
        	}
    	}
	}
	private boolean checkDataBase(){
    	SQLiteDatabase checkDB = null;
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    	}catch(SQLiteException e){
    	}
    	if(checkDB != null) checkDB.close();
    	return checkDB != null ? true : false;
    }
	private void copyDataBase() throws IOException{
    	InputStream myInput = mContext.getAssets().open(DB_NAME);
    	String outFileName = DB_PATH + DB_NAME;
    	OutputStream myOutput = new FileOutputStream(outFileName);
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    }
    public SQLiteDatabase openDataBase(){
    	SQLiteDatabase db = null;
        String myPath = DB_PATH + DB_NAME;
    	db = mDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    	return db;
    }
    public int execInsertCommand(SQLiteDatabase db, String table,ContentValues values){
    	int result =0;
    	try{
    		result = (int) db.insert(table, null, values);
    	}catch(SQLException e){
    		Toast.makeText(mContext, "Thuc thi khong thanh cong: "
					+ e.toString(), 3000).show();
    	}
    	return result;
    }
    public Cursor execQuery(SQLiteDatabase db, String tablename, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy){
    	Cursor c = null;
    	try{
    		c = db.query(tablename, columns, selection, selectionArgs, groupBy, having, orderBy);
    	}catch (SQLException e) {
			Toast.makeText(mContext,
					"Loi khi truy van du lieu:" + e.toString(), 4000).show();
		} 
    	return c;
    }
    public int execUpdateCommand(SQLiteDatabase db,String table,ContentValues values,String whereClause,String []whereArgs){
    	int result = 0;
    	try{
    		result = db.update(table, values, whereClause, whereArgs);
    	}catch (SQLException e)
    	{
    		Toast.makeText(mContext,"Thuc thi khong thanh cong"+e.toString(), 3000).show();
    	}
    	return result;
    }
    public int execDeleteCommand(SQLiteDatabase db,String table,String whereClause,String[] whereArgs){
    	int result =0;
    	try{
    		result = db.delete(table, whereClause, whereArgs);
    	}catch (SQLException e){
    		Toast.makeText(mContext, "Thuc thi khong thanh cong: "
					+ e.toString(), 3000);
    	}
    	return result;
    }
    public Cursor execQuery(SQLiteDatabase db,String sql){
    	Cursor c = null;
    	try{
    		c = db.rawQuery(sql,null);
    	}catch (SQLException e)
    	{
    		System.err.println("Loi truy van du lieu"+e.toString());
    	}
    	return c;
    }
    
}
