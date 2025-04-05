package com.example.nomdesmembresdugroupe;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.auth), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
