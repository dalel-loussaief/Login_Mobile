package com.example.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "compte";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "user";
    public static final String KEY_ID = "id";
    public static final String KEY_Nom = "Nom";
    public static final String KEY_Prenom = "prenom";
    public static final String KEY_Password = "Password";

    // Constructeur
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Création de la table
        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_Nom + " TEXT,"
                + KEY_Prenom + " TEXT,"
                + KEY_Password + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer la table existante et la recréer lors de la mise à niveau de la base de données
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}