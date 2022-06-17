package br.edu.icomp.ufam.icompmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUsers extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Users.db";
    private static final String SQL_CREATE_PASS = "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, function TEXT," +
            "password TEXT, notes TEXT)";
    private static final String SQL_POPULATE_PASS = "INSERT INTO users VALUES " +
            "(NULL, 'Horacio', 'Professor', '1234', 'Nerd de carteirinha como eu')";
    private static final String SQL_DELETE_PASS = "DROP TABLE IF EXISTS users";
    public DatabaseUsers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PASS);
        db.execSQL(SQL_POPULATE_PASS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PASS);
        onCreate(db);
    }
}
