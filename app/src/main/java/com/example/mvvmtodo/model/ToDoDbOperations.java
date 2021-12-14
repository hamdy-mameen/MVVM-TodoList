package com.example.mvvmtodo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ToDoDbOperations {
    SQLiteDatabase db;
    public ToDoDbOperations(Context context){
        TodoDB todoDB = new TodoDB(context);
        db = todoDB.getWritableDatabase();

    }
    public boolean insert(String title,String description) {
        ContentValues values =new ContentValues();
        values.put(TodoContract.TodoEntry.COLOUMN_TITLE,title);
        values.put(TodoContract.TodoEntry.COLOUMN_DESCRIPTION,description);
        long rowId = db.insert(TodoContract.TodoEntry.TABLE_NAME,null,values);
        Log.d("TodoOPERATIONS","rowID =" + rowId);
        if(rowId==-1)
        return false;
        return true;
    }
    public boolean delete(long id) {
        String selection = TodoContract.TodoEntry._ID + " = ?";
        String [] selectionArgs = new String[]{String.valueOf(id)};

        int deletedRows = db.delete(TodoContract.TodoEntry.TABLE_NAME,selection,selectionArgs);
        if(deletedRows==0){
            return false;
        }

        return true;
    }
    public List<ToDo> getAllToDos() {
        List<ToDo> toDos = new ArrayList<>();
        Cursor cursor = db.query(TodoContract.TodoEntry.TABLE_NAME,new String[]{TodoContract.TodoEntry._ID, TodoContract.TodoEntry.COLOUMN_TITLE, TodoContract.TodoEntry.COLOUMN_DESCRIPTION},null,null,null,null,null);
        if(cursor!= null & cursor.getCount()>0){
            while (cursor.moveToNext()){
                int idColumn_index = cursor.getColumnIndex(TodoContract.TodoEntry._ID);
                int titleColumn_index = cursor.getColumnIndex(TodoContract.TodoEntry.COLOUMN_TITLE);
                int descriptionColumn_index = cursor.getColumnIndex(TodoContract.TodoEntry.COLOUMN_DESCRIPTION);
                ToDo toDo = new ToDo(cursor.getString(titleColumn_index),cursor.getString(descriptionColumn_index));
                toDos.add(toDo);
                Log.d("TodoOPERATIONS","cursor"+ cursor.getCount());
            }
        }
        cursor.close();
        return toDos;
    }
}
