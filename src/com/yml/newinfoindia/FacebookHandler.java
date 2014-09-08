package com.yml.newinfoindia;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FacebookHandler extends SQLiteOpenHelper{
	
	private final static int DATABASE_VESRION = 1;
	private final static String DATABASE_NAME = "facebookmaneger";
	private final static String TABLE_FACEBOOK = "facebook";
	private final static String KEY_ID = "id";
	private final static String KEY_NAME = "name";
	private final static String KEY_LOCATION = "location";

	public FacebookHandler(Context context) {
		// TODO Auto-generated constructor stub
		
		super(context, DATABASE_NAME, null, DATABASE_VESRION);
		Log.d("instat", "done");
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table facebook"
				+ "(id integer primary key, name text, location text)");
		Log.d("created success", "success");
		
		}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXIST" + TABLE_FACEBOOK);
		onCreate(db);
		
	}
	
	public void insertFacebook(FacebooKClass facebook)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(KEY_NAME, facebook.getName());
		values.put(KEY_LOCATION, facebook.getLocation());
		
		db.insert(TABLE_FACEBOOK, null, values);
		
		Log.d("insert done", "done");
		db.close();
		
	}
	
	// get single facebook
	
		FacebooKClass getFacebook(int id) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_FACEBOOK, new String[] { KEY_ID,
					KEY_NAME, KEY_LOCATION }, KEY_ID + "=?",
					new String[] { String.valueOf(id) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			FacebooKClass facebook = new FacebooKClass(Integer.parseInt(cursor.getString(0)),
		            cursor.getString(1), cursor.getString(2));
			// return student
			return facebook;

		}
		
		//get all facebook details
		
		public List<FacebooKClass> getAllFacebook()
		{
			List<FacebooKClass> facebookList=new ArrayList<FacebooKClass>();
			
			String selectQuery="SELECT * FROM "+TABLE_FACEBOOK;
			
			SQLiteDatabase db=this.getWritableDatabase();
			
			Cursor cursor=db.rawQuery(selectQuery, null);
			
			if (cursor.moveToFirst())
			{
				do
				{
					FacebooKClass facebook=new FacebooKClass();
					 facebook.setId(Integer.parseInt(cursor.getString(0)));
			            facebook.setName(cursor.getString(1));
			            facebook.setLocation(cursor.getString(2));
			            facebookList.add(facebook);
			            
			}while(cursor.moveToNext());
		}
		return facebookList;
		
		
	

		}
}
