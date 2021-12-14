package com.example.mvvmtodo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mvvmtodo.model.TodoContract.TodoEntry;

import java.util.ArrayList;
import java.util.List;

public class TodoDB extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DATABASE_NAME ="todo.db";
    private static final int DATABASE_VERSION =1;
    public TodoDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

        //db = new TodoDB(context).getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoEntry.TABLE_NAME + "(" +
                TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TodoEntry.COLOUMN_TITLE + " TEXT NOT NULL, " +
                TodoEntry.COLOUMN_DESCRIPTION + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TODO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
