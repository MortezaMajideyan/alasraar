package com.pattern.sarketab.ui.Sarketab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class helper_location extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "location.db";
    public static final String TABLE_NAME = "location";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TXT = "txt";
    public static final String COLUMN_TXT2 = "txt2";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_LAN = "lang";
    private HashMap hp;

    public helper_location(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+TABLE_NAME +
                        "("+COLUMN_ID+" INTEGER primary key, "+COLUMN_TXT+" TEXT,"+COLUMN_TXT2+" TEXT,"+COLUMN_CODE+" TEXT," +
                        COLUMN_LAN+" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertLocation (String txt, String txt2, String code, String lan) {
        Cursor res = getData(code);
        if (res.getCount() > 0){
            updateLocation(txt, txt2, code, lan);
        }else{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_TXT , encode(txt));
            contentValues.put(COLUMN_TXT2 , encode(txt2));
            contentValues.put(COLUMN_CODE , encode_code(code));
            contentValues.put(COLUMN_LAN , encode_code(lan));
            db.insert(TABLE_NAME, null, contentValues);
        }
        return true;
    }

    public Cursor getData(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_CODE+"='"+encode_code(code)+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateLocation (String txt, String txt2, String code, String lan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TXT , encode(txt));
        contentValues.put(COLUMN_TXT2 , encode(txt2));
        contentValues.put(COLUMN_CODE , encode_code(code));
        contentValues.put(COLUMN_LAN , encode_code(lan));
        db.update(TABLE_NAME, contentValues, COLUMN_CODE+" = ? ", new String[] { encode_code(code) } );
        return true;
    }

    public Integer deleteLocation(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllLocation() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_TXT)));
            res.moveToNext();
        }
        return array_list;
    }

    private String encode(String text){
        //Log.e("md11" , text);
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        String decode = base64.replace("="," ");
        //Log.e("md1" , base64);
       // Log.e("md3" , decode);
        String salt = getSaltString();
        decode=salt+decode;
       //Log.e("md5" , decode+":");
        return decode;
    }


    private String encode_code(String text){
        //Log.e("md11" , text);
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        String decode = base64.replace("="," ");
        return decode;
    }


    public String decode(String text){
        String text_decode = text.replace(" " , "=");
        byte[] en = Base64.decode(text_decode.substring(2,text_decode.length()),Base64.NO_WRAP);
        String val2 = "";
        try {
            val2 = new String(en, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return val2;
    }


    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrst";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() <= 1) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}