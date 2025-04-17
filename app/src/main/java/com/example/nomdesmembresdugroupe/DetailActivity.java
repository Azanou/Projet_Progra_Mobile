package com.example.nomdesmembresdugroupe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomdesmembresdugroupe.data.CategoryAdapter;
import com.example.nomdesmembresdugroupe.data.Product;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView badgeTextView; // Affiche le nombre d'articles au panier
    private int cartItemCount = 0; // Compte les articles au panier

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Permet à l'app d'aller bord à bord
        setContentView(R.layout.activity_detail); // Lie le layout XML à cette activité

        Toolbar toolbar = findViewById(R.id.my_toolbar); // Récupère la barre d'outils
        setSupportActionBar(toolbar); // Définit la barre d'outils comme barre d'action

        // Configuration de la liste des catégories (onglets)
        List<String> categories = Arrays.asList("Tous", "Musique", "Podcasts", "Universités", "Rap français",
                "Hackers", "Mix", "Intelligence artificielle", "Entrepreneuriat",
                "En direct", "Musique africaine", "Programmation informatique", "Histoire");
        RecyclerView r = findViewById(R.id.category_recycler);
        CategoryAdapter adapter = new CategoryAdapter(categories);
        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r.setAdapter(adapter);

        // Récupération des vues pour afficher les détails du produit
        ImageView productImage = findViewById(R.id.productImage);
        TextView nomProduit = findViewById(R.id.productName),
                descriptionProduit = findViewById(R.id.productDescription),
                quantiteProduitTextView = findViewById(R.id.productQuantity),
                prixProduit = findViewById(R.id.productPrice);

        // Récupération des boutons Ajouter et Soustraire
        Button add = findViewById(R.id.buttonAdd);
        Button substract = findViewById(R.id.buttonSubstract);

        // Récupération des données du produit via l'Intent
        Intent i = getIntent();
        Product currentProduct = null;
        if (i != null && i.hasExtra("produit")) {
            Product p = (Product) i.getSerializableExtra("produit");
            if (p != null) {
                // Affichage des détails du produit
                productImage.setImageResource(p.getImage());
                nomProduit.setText(p.getNomImage());
                descriptionProduit.setText(p.getDescription());
                quantiteProduitTextView.setText("Quantite: " + p.getQuantite());
                prixProduit.setText("Prix: " + p.getPrixUnitaire());
                currentProduct = p;
            }
        }

        final Product finalCurrentProduct = currentProduct; // Référence finale au produit
        final TextView finalQuantiteProduitTextView = quantiteProduitTextView; // Référence finale au TextView de quantité
        final TextView finalPrixProduitTextView = prixProduit; // Référence finale au TextView de prix

        // Action du bouton Ajouter
        add.setOnClickListener(v -> {
            if (finalCurrentProduct != null && finalCurrentProduct.getQuantite() > 0) {
                finalCurrentProduct.setQuantiteLess(); // Diminue la quantité du produit
                finalQuantiteProduitTextView.setText("Quantite: " + finalCurrentProduct.getQuantite()); // Met à jour l'affichage
                finalPrixProduitTextView.setText("Prix: " + finalCurrentProduct.getPrixUnitaire() * (cartItemCount + 1)); // Met à jour le prix
                cartItemCount++; // Incrémente le compteur du panier
                updateCartBadge(); // Met à jour le badge du panier
            }
        });

        // Action du bouton Soustraire
        substract.setOnClickListener(v -> {
            if (cartItemCount > 0 && finalCurrentProduct != null) {
                finalCurrentProduct.setQuantitePlus(); // Augmente la quantité du produit
                finalQuantiteProduitTextView.setText("Quantite: " + finalCurrentProduct.getQuantite()); // Met à jour l'affichage
                cartItemCount--; // Décrémente le compteur du panier
                if (cartItemCount > 0) {
                    finalPrixProduitTextView.setText("Prix: " + finalCurrentProduct.getPrixUnitaire() * cartItemCount); // Met à jour le prix
                } else {
                    finalPrixProduitTextView.setText("Prix: " + finalCurrentProduct.getPrixUnitaire()); // Réinitialise le prix au prix unitaire
                }
                updateCartBadge(); // Met à jour le badge du panier
            }
        });

        // Gestion des marges pour les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu); // Charge le menu de la barre d'outils
        MenuItem menuItem = menu.findItem(R.id.action_cart); // Récupère l'élément du panier
        View actionView = menuItem.getActionView(); // Récupère la vue personnalisée du panier
        if (actionView != null) {
            badgeTextView = actionView.findViewById(R.id.cart_badge); // Récupère le TextView du badge
            updateCartBadge(); // Met à jour l'affichage initial du badge
        }
        return true;
    }

    // Met à jour l'affichage du badge du panier
    private void updateCartBadge() {
        if (badgeTextView == null) return;
        badgeTextView.setVisibility(cartItemCount == 0 ? View.GONE : View.VISIBLE);
        if (cartItemCount > 0) {
            badgeTextView.setText(String.valueOf(cartItemCount));
        }
    }
}