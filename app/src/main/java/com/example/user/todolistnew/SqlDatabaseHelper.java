package com.example.user.todolistnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05/09/2016.
 */

// This Class handles of all the SQL statements and sets up the CRUD functionality of the database
// These methods can then be called in MainActivity


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

    // Specifies what values every item in the database should have
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE =
                "CREATE TABLE " + TABLE_TODO + " (" +
                KEY_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                KEY_TODO_ITEM_NAME + " TEXT," +
                KEY_TODO_ITEM_PRIORITY + " TEXT," +
                KEY_TODO_ITEM_DUEBY + " TEXT" +
                ")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    // CHECKS IF DB EXISTS AND DROPS TABLE IF NECESSARY
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
            onCreate(db);
        }
    }

    // ADD ITEM TO DATABASE
    public long addDb(ToDoItem toDoItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TODO_ITEM_NAME,toDoItem.getName());
        contentValues.put(KEY_TODO_ITEM_PRIORITY,toDoItem.getPriority());
        contentValues.put(KEY_TODO_ITEM_DUEBY,toDoItem.getDuedate());

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_TODO,null,contentValues);
        db.close();
        return id;
    }


    // DELETE ITEM FROM DATABASE
    public boolean deleteDb(String name){
        boolean result = false;
        String query = "Select * FROM " + TABLE_TODO + " where " + KEY_TODO_ITEM_NAME + " LIKE '" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ToDoItem toDoItem = new ToDoItem();

        if (cursor.moveToFirst()) {
            toDoItem.setId(cursor.getInt(0));
            db.delete(TABLE_TODO, KEY_TODO_ID + " = ?",
                    new String[] { String.valueOf(toDoItem.getId()) });
            cursor.close();
            result = true;
        }
        db.close();

        return result;
    }


    // RETRIEVE SINGLE ITEM FROM DATABASE
    public ToDoItem getToDoItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TODO, new String[]{KEY_TODO_ID,
                        KEY_TODO_ITEM_NAME, KEY_TODO_ITEM_PRIORITY, KEY_TODO_ITEM_DUEBY}, KEY_TODO_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ToDoItem item = new ToDoItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return item;
    }


    // GET ALL ITEMS FROM DATABASE
    public List<ToDoItem> getAllItems() {
        List<ToDoItem> itemList = new ArrayList<ToDoItem>();
        String selectQuery = "SELECT * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ToDoItem toDoItem;
        if (cursor.moveToFirst()) {
            do {
                toDoItem = new ToDoItem();
                toDoItem.setId(cursor.getInt(0));
                toDoItem.setName(cursor.getString(1));
                toDoItem.setPriority(cursor.getString(2));
                toDoItem.setDuedate(cursor.getString(3));
                itemList.add(toDoItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemList;
    }


    // UPDATE ITEM IN DATABASE
    public void updateItem(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strFilter = KEY_TODO_ID+"=" + ((Long)toDoItem.getId()).intValue();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_TODO_ITEM_NAME,toDoItem.getName());
        contentValues.put(KEY_TODO_ITEM_PRIORITY,toDoItem.getPriority());
        contentValues.put(KEY_TODO_ITEM_DUEBY,toDoItem.getDuedate());

        int ret = db.update(TABLE_TODO, contentValues, strFilter, null);
        db.close();
    }

}
