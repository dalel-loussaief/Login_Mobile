package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private EditText nomEditText, prenomEditText, passwordEditText;
    private Button creerButton;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomEditText = findViewById(R.id.editTextText);
        prenomEditText = findViewById(R.id.editTextText2);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        Button connecterButton = findViewById(R.id.button2);
        connecterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Démarrer l'activité MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        creerButton = findViewById(R.id.button);
        creerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs saisies
                String nom = nomEditText.getText().toString();
                String prenom = prenomEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Insérer un nouvel utilisateur dans la base de données
                long result = dbManager.insertUser(nom, prenom, password);

                // Gérer le résultat de l'insertion (par exemple, afficher un message)
                if (result != -1) {
                    // Succès
                    showToast("Utilisateur bien enregistré");
                } else {
                    // Échec
                    showToast("Échec de l'enregistrement de l'utilisateur");
                }
            }
        });

        // Initialiser le gestionnaire de base de données
        dbManager = new DBManager(this);
        dbManager.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the DBManager instance
        dbManager.close();
    }

    // Méthode pour afficher un Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
