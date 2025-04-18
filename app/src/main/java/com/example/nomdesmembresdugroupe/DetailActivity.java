// DetailActivity.java
package com.example.nomdesmembresdugroupe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomdesmembresdugroupe.data.CategoryAdapter;
import com.example.nomdesmembresdugroupe.data.Product;
import com.example.nomdesmembresdugroupe.fragment.CategoryFragment;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView badgeTextView;
    private int cartItemCount = 0;
    private TextView quantiteProduitTextView; // Affiche la quantité DISPONIBLE du produit (diminue)
    private TextView prixProduitTextView;    // Affiche le prix TOTAL dans le panier (augmente)
    private Product currentProduct;
    private boolean isFragmentDisplayed = false;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CartState";
    private static final String KEY_CART_COUNT = "cart_count_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Log.d("DetailActivity", "onCreate: SharedPreferences récupérées.");

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        List<Integer> categories = Arrays.asList(
                R.string.Telephone, R.string.Accesiores_mobile, R.string.Appareils_audio, R.string.Batteries_externes,
                R.string.pc
        );
        RecyclerView r = findViewById(R.id.category_recycler);
        CategoryAdapter adapter = new CategoryAdapter(categories, categoryName -> {
            Fragment selectedFragment = null;
            switch (categoryName) {
                case "Telephone":
                    selectedFragment = CategoryFragment.newInstance(categoryName);
                    break;
                case "Accessoires mobiles":
                    selectedFragment = CategoryFragment.newInstance(categoryName);
                    break;
                case "Appareils audio":
                    selectedFragment = CategoryFragment.newInstance(categoryName);
                    break;
                case "Batteries externes":
                    selectedFragment = CategoryFragment.newInstance(categoryName);
                    break;
                case "Casques":
                    selectedFragment = CategoryFragment.newInstance(categoryName);
                    break;
                case "Ordinateurs":
                    selectedFragment = CategoryFragment.newInstance(categoryName);
                    break;
            }

            if (selectedFragment != null) {
                if (!isFragmentDisplayed) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
                    Log.d("FRAGMENT", "Fragment affiché !");
                    isFragmentDisplayed = true;
                } else {
                    findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);
                    isFragmentDisplayed = false;
                }
            }
        });
        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r.setAdapter(adapter);

        ImageView productImage = findViewById(R.id.productImage);
        TextView nomProduit = findViewById(R.id.productName),
                descriptionProduit = findViewById(R.id.productDescription);
        quantiteProduitTextView = findViewById(R.id.productQuantity);
        prixProduitTextView = findViewById(R.id.productPrice);

        Button add = findViewById(R.id.buttonAdd),
                substract = findViewById(R.id.buttonSubstract);

        Intent i = getIntent();
        if (i != null && i.hasExtra("produit")) {
            currentProduct = (Product) i.getSerializableExtra("produit");
            if (currentProduct != null) {
                productImage.setImageResource(currentProduct.getImage());
                nomProduit.setText(currentProduct.getNomImage());
                descriptionProduit.setText(currentProduct.getDescription());

                loadCartState(); // Charge la quantité déjà dans le panier
                // Affichage initial de la quantité disponible et du prix (prix unitaire au départ)
                quantiteProduitTextView.setText("Quantité: " + currentProduct.getQuantite());
                prixProduitTextView.setText("Prix: " + currentProduct.getPrixUnitaire());
                updateCartBadge();
            }
        }

        add.setOnClickListener(v -> {
            if (currentProduct != null && currentProduct.getQuantite() > cartItemCount) {
                cartItemCount++;
                currentProduct.setQuantiteLess(); // Diminue la quantité disponible
                updateQuantityAndPriceDisplay();
                updateCartBadge();
                saveCartState();
            } else if (currentProduct != null) {
                Toast.makeText(this, "Quantité maximale atteinte", Toast.LENGTH_SHORT).show();
            }
        });

        substract.setOnClickListener(v -> {
            if (cartItemCount > 0 && currentProduct != null) {
                cartItemCount--;
                currentProduct.setQuantitePlus(); // Augmente la quantité disponible
                updateQuantityAndPriceDisplay();
                updateCartBadge();
                saveCartState();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DetailActivity", "onStop: cartItemCount = " + cartItemCount);
        // Laisser la réinitialisation ici COMMENTÉE pour conserver le panier de ce produit
        // tant que l'application n'est pas fermée.
        // cartItemCount = 0;
        // updateCartBadge();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Rends la méthode publique
        Log.d("DetailActivity", "onCreateOptionsMenu: Menu créé.");
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();
        if (actionView != null) {
            badgeTextView = actionView.findViewById(R.id.cart_badge);
            updateCartBadge();
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getCartKey() {
        return KEY_CART_COUNT + (currentProduct != null ? currentProduct.getNomImage() : "default_product");
    }

    private void saveCartState() {
        if (currentProduct != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getCartKey(), cartItemCount);
            editor.apply();
            Log.d("DetailActivity", "saveCartState: Sauvegarde de " + cartItemCount + " pour " + currentProduct.getNomImage());
        }
    }

    private void loadCartState() {
        if (currentProduct != null) {
            cartItemCount = sharedPreferences.getInt(getCartKey(), 0);
            Log.d("DetailActivity", "loadCartState: Chargement de " + cartItemCount + " pour " + currentProduct.getNomImage());
            updateQuantityAndPriceDisplay();
            updateCartBadge();
        } else {
            Log.d("DetailActivity", "loadCartState: currentProduct est null.");
        }
    }

    private void updateCartBadge() {
        if (badgeTextView == null) {
            Log.d("DetailActivity", "updateCartBadge: badgeTextView est null.");
            return;
        }
        badgeTextView.setVisibility(cartItemCount == 0 ? View.GONE : View.VISIBLE);
        if (cartItemCount > 0) {
            badgeTextView.setText(String.valueOf(cartItemCount));
            Log.d("DetailActivity", "updateCartBadge: Affichage de " + cartItemCount);
        } else {
            Log.d("DetailActivity", "updateCartBadge: Panier vide, badge caché.");
        }
    }

    // Met à jour l'affichage de la quantité disponible et du prix total dans le panier
    private void updateQuantityAndPriceDisplay() {
        quantiteProduitTextView.setText("Quantité: " + currentProduct.getQuantite());
        prixProduitTextView.setText("Prix: " + cartItemCount * currentProduct.getPrixUnitaire());
    }
}