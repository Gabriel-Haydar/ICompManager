package br.edu.icomp.ufam.icompmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Documentos.db";
    private static final String SQL_CREATE_PASS = "CREATE TABLE documentos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT, interessado TEXT," +
            "tipo TEXT, descricao TEXT, armazenamento TEXT, armazenamentoCompleto TEXT)";
    private static final String SQL_POPULATE_PASS = "INSERT INTO documentos VALUES " +
            "(NULL, '25/12/0001', 'Humanidade', 'Certidão de Nascimento', 'Jesus nasceu nesta data. Se não sabia é HEREGE!', 'Belém','Belém, na Cisjordânia')";
    private static final String SQL_DELETE_PASS = "DROP TABLE IF EXISTS documentos";
    public Database(Context context) {
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
