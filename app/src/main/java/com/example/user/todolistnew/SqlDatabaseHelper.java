package com.example.user.todolistnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 05/09/2016.
 */
public class SqlDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "listDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO = "todoitems";
    private static final String KEY_TODO_ID = "_id";
    private static final String KEY_TODO_ITEM_NAME = "item";
    private static final String KEY_TODO_ITEM_PRIORITY = "priority";
    private static final String KEY_TODO_ITEM_DUEBY = "dueby";


    public SqlDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO +
                " (" +
                KEY_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                KEY_TODO_ITEM_NAME + " TEXT," +
                KEY_TODO_ITEM_PRIORITY + " TEXT," +
                KEY_TODO_ITEM_DUEBY + " DATETIME" +
                ")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
            onCreate(db);
        }
    }

}
