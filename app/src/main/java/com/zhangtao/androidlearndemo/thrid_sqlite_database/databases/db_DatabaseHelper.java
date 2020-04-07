package com.zhangtao.androidlearndemo.thrid_sqlite_database.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class db_DatabaseHelper extends SQLiteOpenHelper {

    final String TAG = "db_DatabaseHelper";
    /**
     *
     *
     * @param context
     */

    public db_DatabaseHelper(@Nullable Context context) {
        super(context, db_Constants.DATABASE_NAME, null, db_Constants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建时回调
        //创建字段
        // sql: create table table_name(_id integer, name varchar(20),age integer, salary integer);
        String sql = "create table " + db_Constants.TABLE_NAME +"(_id integer, name varchar(20),age integer, salary integer);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //升级数据库时回调

        //sql： alter table table_name add phone integeer;
        if(oldVersion == 1){
            String sql = "alter table " + db_Constants.TABLE_NAME + " add phone varchar";
            db.execSQL(sql);
            sql = "alter table " + db_Constants.TABLE_NAME + " add address varchar";
            db.execSQL(sql);
        }
    }
}
