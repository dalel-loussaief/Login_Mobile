package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }
    // Inside your DBManager class or wherever you perform database operations
    public long insertUser(String nom, String prenom, String password) {
        long result = -1;

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_Nom, nom);
            contentValues.put(DBHelper.KEY_Prenom, prenom);
            contentValues.put(DBHelper.KEY_Password, password);

            result = db.insertOrThrow(DBHelper.TABLE_USER, null, contentValues);
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }

        return result;
    }

    public boolean isUserExists(String nom, String prenom) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {DBHelper.KEY_ID};

        // Specify the WHERE clause with the user's name
        String selection = DBHelper.KEY_Nom + " = ? AND " + DBHelper.KEY_Prenom + " = ?";
        String[] selectionArgs = {nom, prenom};

        // Query the database
        Cursor cursor = db.query(
                DBHelper.TABLE_USER, // Table to query
                columns,            // Columns to return
                selection,          // The columns for the WHERE clause
                selectionArgs,      // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // don't sort order
        );

        boolean userExists = cursor.moveToFirst();
        cursor.close();
        return userExists;
    }
}


