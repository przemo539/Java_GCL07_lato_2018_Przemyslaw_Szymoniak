package przemo.javaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "AppDb.db";
    public static final String TABLE_NAME = "User_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "BIRTHDATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,PASSWORD TEXT,BIRTHDATE TEXT)");
        db.execSQL("create table Activity (ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, Distance INTEGER, Duration INTEGER, MaxSpeed NUMERIC)");
        db.execSQL("create table Activity_Point (ID INTEGER PRIMARY KEY AUTOINCREMENT, Activity_ID INTEGER, Latitude TEXT, Longitude TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String name,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,"");
        long result = db.insert(TABLE_NAME,null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public long insertActivity(int User_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_ID",User_ID);
        contentValues.put("Distance",0);
        contentValues.put("Duration",0);
        contentValues.put("MaxSpeed",0.0);
        long result = db.insert("Activity",null ,contentValues);
        return result;
     }
    public boolean insertActivity_Point(long Activity_ID, double Latitude, double Longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Activity_ID",Activity_ID);
        contentValues.put("Latitude",Latitude);
        contentValues.put("Longitude",Longitude);
        long result = db.insert("Activity_Point",null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllActivities(int User_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Activity Where User_ID='"+User_ID+"'",null);
        return res;
    }
    public Cursor getActivities(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Activity Where ID='"+ID+"'",null);
        return res;
    }


    public Cursor getAllPoints(int Activity_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Activity_Point Where Activity_ID='"+Activity_ID+"'",null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public int getToLogin(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id from "+TABLE_NAME+" WHERE NAME= '"+name+"' and PASSWORD='"+password+"'", null);
        int ret=-1;
        if(res.moveToLast()){
            ret = res.getInt(0);
        }
        return ret;
    }

    public boolean updateActivity(long id, int Distance, int Duration,float MaxSpeed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put("Distance",Distance);
        contentValues.put("Duration",Duration);
        contentValues.put("MaxSpeed",MaxSpeed);
        db.update("Activity", contentValues, "ID = ?",new String[] { Long.toString(id) });
        return true;
    }

    public boolean updateData(String id,String name,String surname,String BIRTHDATE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,BIRTHDATE);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}