package com.example.mvvmtodo.model;

import android.provider.BaseColumns;

public final class TodoContract {
    private TodoContract(){

    }

public final static class TodoEntry implements BaseColumns{
    public final static String TABLE_NAME ="todos";
    public final static String _ID =BaseColumns._ID;
    public final static String COLOUMN_TITLE ="title";
    public final static String COLOUMN_DESCRIPTION ="description";
}

}
