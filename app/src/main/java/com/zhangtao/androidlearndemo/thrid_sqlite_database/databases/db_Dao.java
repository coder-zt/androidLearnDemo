package com.zhangtao.androidlearndemo.thrid_sqlite_database.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class db_Dao {
    private final String TAG = "db_Dao";
    private final db_DatabaseHelper mhelper;
    public db_Dao(Context context){
        mhelper = new db_DatabaseHelper(context);
    }

    public void insert(){
        SQLiteDatabase db = mhelper.getWritableDatabase();
//        String sql = "insert into " + db_Constants.TABLE_NAME + "(_id,name,age,salary,phone,address)values(?,?,?,?,?,?)";
//        db.execSQL(sql, new Object[]{1,"张滔",21,3500,"13678088714","重庆开县"});
        ContentValues values = new ContentValues();
        values.put("_id", 1);
        values.put("name", "张滔");
        values.put("salary", 3500);
        values.put("phone", "13678088714");
        values.put("address", "重庆开县");
        db.insert(db_Constants.TABLE_NAME, null, values);
        db.close();
    }

    public void delete(){
        SQLiteDatabase db = mhelper.getWritableDatabase();
//        String sql = "delete from " + db_Constants.TABLE_NAME + " where age = 30";
//        db.execSQL(sql);
        String[] Args = new String[1];
        Args[0] = "30";
        db.delete(db_Constants.TABLE_NAME,"age = ?",Args);
        db.close();
    }

    public void updata(){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        //String sql = "update " + db_Constants.TABLE_NAME + " set salary = 6500 where age =21";
        //db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("salary", 6500);
        String[] Args = new String[1];
        Args[0] = "30";
        db.update(db_Constants.TABLE_NAME, values, "age = ?", Args);
        db.close();
    }

    public void query(){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        // String sql = "select * from " + db_Constants.TABLE_NAME;
        // Cursor cursor = db.rawQuery(sql,null);
        // while(cursor.moveToNext()){
        //     int index = cursor.getColumnIndex("name");
        //     String name = cursor.getString(index);
        //     Log.d(TAG, name);
        // }
        // cursor.close();
        Cursor cursor = db.query(db_Constants.TABLE_NAME, null, null, null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex("name");
            String name = cursor.getString(index);
            Log.d(TAG, name);
        }
        db.close();
    }
}
