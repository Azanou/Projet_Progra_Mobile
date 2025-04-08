package com.example.nomdesmembresdugroupe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nomdesmembresdugroupe.data.UserCredentials;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //on recupere les elements de la vue activity_register

        EditText username = findViewById(R.id.newName);
        EditText password = findViewById(R.id.newPassword);
        Button register = findViewById(R.id.registering);
        Button goBack = findViewById(R.id.goBack);
        TextView errorRegister = findViewById(R.id.registerError);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // logique d'enregistrement d'un nouvel utilisateur
        register.setOnClickListener(v -> {

            String freshUsername = username.getText().toString().trim();
            String freshPassword = password.getText().toString().trim();
            // System.out.println(freshUsername.getText());
            // System.out.println(freshPassword.getText());
            if ( !freshUsername.isEmpty() ){
                if(!UserCredentials.USER_CREDENTIALS.containsKey(freshPassword)){

                    UserCredentials.addUserCredentials(freshPassword, freshUsername);
                    Toast.makeText(RegisterActivity.this,"Enregistrement Reussie !", Toast.LENGTH_SHORT ).show();
                    errorRegister.setVisibility(View.INVISIBLE);
                }else{
                    errorRegister.setText("veuillez changer de password");
                    errorRegister.setVisibility(View.VISIBLE);
                }
               // System.out.println(UserCredentials.USER_CREDENTIALS.values());
            }else{
                errorRegister.setText("veuillez remplir tous les champs");
                errorRegister.setVisibility(View.VISIBLE);
            }

        });

        //retour a la page d'authentification
        goBack.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, AuthActivity.class);
            startActivity(i);
        });
    }
}
