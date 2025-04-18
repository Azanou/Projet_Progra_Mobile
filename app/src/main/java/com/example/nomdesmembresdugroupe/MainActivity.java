// MainActivity.java
package com.example.nomdesmembresdugroupe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomdesmembresdugroupe.data.Product;
import com.example.nomdesmembresdugroupe.data.ProductAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//Voici dont la classe en relation avec notre page d'acceuil une fois la l'utilisateur authentifier.
//C'est ici meme qu'on implementera la logique pour afficher nos la liste de nos produits

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CartState"; // Le même nom que dans DetailActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // **Réinitialisation de l'état du panier AU TOUT DÉBUT de onCreate()**
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Efface toutes les préférences liées au panier
        editor.apply();
        Log.d("MainActivity", "État du panier réinitialisé au démarrage de l'application.");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // Récupérer la liste des produits
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Liste_produits")) {
            ArrayList<Product> products;
            products = (ArrayList<Product>) intent.getSerializableExtra("Liste_produits");

            //on trouve et on configure le recyclerView dans le layout
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ProductAdapter(products);
            recyclerView.setAdapter(adapter);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}