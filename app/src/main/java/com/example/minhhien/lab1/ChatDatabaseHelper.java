package com.example.minhhien.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Frederic on 10-Oct-16.
 */
public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Chats.db";
    public static int VERSION_NUM = 4;
    public static String TABLE_NAME = "AND_TABLE_db";
    public static String KEY_ID = "ID", KEY_MESSAGE = "MESSAGE";
    protected static String ACTIVITY_NAME = "ChatDatabaseHelper";

    public ChatDatabaseHelper (Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MESSAGE + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE_SQL);
        Log.i(ACTIVITY_NAME, "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
