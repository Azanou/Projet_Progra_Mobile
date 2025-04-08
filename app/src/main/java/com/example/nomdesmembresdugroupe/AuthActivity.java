package com.example.nomdesmembresdugroupe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nomdesmembresdugroupe.data.Product;
import com.example.nomdesmembresdugroupe.data.ProductData;
import com.example.nomdesmembresdugroupe.data.UserCredentials;

import java.util.ArrayList;
import java.util.List;

// C'est cette classe qui gerera la logique d'authentification , notamment en verifiant

// si les infos entres par l'utilisateur sont dans notre base de donnees (qui est juste

// une table de type HashMap<String, String> ) dans notre cas.

//------->>>Redirection vers la classe MainActivity si succès, ou message d'erreur via un TextView.

//------->>>Redirection vers RegisterActivity si l'utilisateur souhaite s'inscrire


public class AuthActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auth);

        List<Product> produits = ProductData.getProducts();

        //on recupere des elements de la vue: Boutons et champs de saisies

        EditText name = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button connection = findViewById(R.id.connection);
        Button registering = findViewById(R.id.register);
        TextView  errorText = findViewById(R.id.textError);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.auth), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //on verifie si l'utilisateur est inscrit. si oui, il est renvoyer vers la page d'acceuil

        connection.setOnClickListener(view -> {

            String UserPassword = password.getText().toString().trim();
            String userName = name.getText().toString().trim();

            //  System.out.println(UserPassword);
            // System.out.println(userName);
            if ( !userName.isEmpty() ){
                    if(UserCredentials.USER_CREDENTIALS.containsKey(UserPassword) && !UserPassword.isEmpty()){

                    String trueUserName = UserCredentials.USER_CREDENTIALS.get(UserPassword);
                    if(userName.equals(trueUserName)) {
                        errorText.setVisibility(View.INVISIBLE);
                        Intent I = new Intent(AuthActivity.this, MainActivity.class);
                        I.putExtra("Liste_produits", new ArrayList<>(produits));
                        startActivity(I);
                    }
                }else{
                        errorText.setText("identifiant(s) incorrecte(s)");
                        errorText.setVisibility(View.VISIBLE);
                    }
            }else{
                errorText.setText("Veuillez remplir tous les champs");
                errorText.setVisibility(View.VISIBLE);
            }
        });

        //On enregistre un nouvel utilisateur
        registering.setOnClickListener(v -> {
            Intent I = new Intent(AuthActivity.this, RegisterActivity.class);
            startActivity(I);
        });

    }
}
