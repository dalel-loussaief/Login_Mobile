package com.example.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Acceuilpage extends AppCompatActivity {
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilpage);

        // Initialize the DBManager and open the database
        dbManager = new DBManager(this);
        dbManager.open();

        // Your other initialization code goes here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the database connection when the activity is destroyed
        if (dbManager != null) {
            dbManager.close();
        }
    }
}
