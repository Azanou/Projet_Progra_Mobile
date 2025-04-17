package com.example.nomdesmembresdugroupe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetailActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {

    private TextView badgeTextView;
    private int cartItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        List<Integer> categories = Arrays.asList(
                R.string.category_tous,
                R.string.category_musique,
                R.string.category_podcasts,
                R.string.category_universites,
                R.string.category_rap_francais,
                R.string.category_hackers,
                R.string.category_mix,
                R.string.category_ia,
                R.string.category_entrepreneuriat,
                R.string.category_en_direct,
                R.string.category_musique_africaine,
                R.string.category_programmation,
                R.string.category_histoire
        );
        RecyclerView r = findViewById(R.id.category_recycler);
        CategoryAdapter adapter = new CategoryAdapter(categories, this);
        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r.setAdapter(adapter);

        ImageView productImage = findViewById(R.id.productImage);
        TextView nomProduit = findViewById(R.id.productName),
                descriptionProduit = findViewById(R.id.productDescription),
                quantiteProduitTextView = findViewById(R.id.productQuantity),
                prixProduit = findViewById(R.id.productPrice);

        Button add = findViewById(R.id.buttonAdd);
        Button substract = findViewById(R.id.buttonSubstract);

        Intent i = getIntent();
        Product currentProduct = null;
        if (i != null && i.hasExtra("produit")) {
            Product p = (Product) i.getSerializableExtra("produit");
            if (p != null) {
                productImage.setImageResource(p.getImage());
                nomProduit.setText(p.getNomImage());
                descriptionProduit.setText(p.getDescription());
                quantiteProduitTextView.setText("Quantite: " + p.getQuantite());
                prixProduit.setText("Prix: " + p.getPrixUnitaire());
                currentProduct = p;
            }
        }

        final Product finalCurrentProduct = currentProduct;
        final TextView finalQuantiteProduitTextView = quantiteProduitTextView;
        final TextView finalPrixProduitTextView = prixProduit;

        add.setOnClickListener(v -> {
            if (finalCurrentProduct != null && finalCurrentProduct.getQuantite() > 0) {
                finalCurrentProduct.setQuantiteLess();
                finalQuantiteProduitTextView.setText("Quantite: " + finalCurrentProduct.getQuantite());
                finalPrixProduitTextView.setText("Prix: " + finalCurrentProduct.getPrixUnitaire() * (cartItemCount + 1));
                cartItemCount++;
                updateCartBadge();
            }
        });

        substract.setOnClickListener(v -> {
            if (cartItemCount > 0 && finalCurrentProduct != null) {
                finalCurrentProduct.setQuantitePlus();
                finalQuantiteProduitTextView.setText("Quantite: " + finalCurrentProduct.getQuantite());
                cartItemCount--;
                if (cartItemCount > 0) {
                    finalPrixProduitTextView.setText("Prix: " + finalCurrentProduct.getPrixUnitaire() * cartItemCount);
                } else {
                    finalPrixProduitTextView.setText("Prix: " + finalCurrentProduct.getPrixUnitaire());
                }
                updateCartBadge();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            badgeTextView = actionView.findViewById(R.id.cart_badge);
            updateCartBadge();
        }
        return true;
    }

    private void updateCartBadge() {
        if (badgeTextView == null) return;
        badgeTextView.setVisibility(cartItemCount == 0 ? View.GONE : View.VISIBLE);
        if (cartItemCount > 0) {
            badgeTextView.setText(String.valueOf(cartItemCount));
        }
    }

    @Override
    public void onCategoryClick(String categoryName) {
        Toast.makeText(this, "Catégorie cliquée : " + categoryName, Toast.LENGTH_SHORT).show();
        // Ici, tu peux implémenter la logique pour filtrer les produits en fonction de la catégorie cliquée.
    }
}