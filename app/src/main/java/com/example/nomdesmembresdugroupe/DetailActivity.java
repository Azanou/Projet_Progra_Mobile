package com.example.nomdesmembresdugroupe;

import android.content.Intent;
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
import androidx.annotation.NonNull;
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
import com.example.nomdesmembresdugroupe.fragment.TelephoneFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//C'est ici que nous afficherons les details par rapport a un produit selectionner
//depuis notre page d'acceuil. cette activite (c'est le nom donner a une page dans une app android)
// contiendra notamment une barre de tache (Toolbar)

public class DetailActivity extends AppCompatActivity {

    // creation de variable action_home pour l'action
    private MenuItem action_home;

    // pour gerer le l'ajout de produit
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
           R.string.Telephone, R.string.Accesiores_mobile,R.string.Appareils_audio,R.string.Batteries_externes,
                R.string.Chargeurs
        );
        //on gere ici l'affichage de notre liste d'onglets dans le RecyclerView
        RecyclerView r = findViewById(R.id.category_recycler);

        //ici, on creer l'adapter et les fragments associes a chaque categories
        CategoryAdapter adapter = new CategoryAdapter(categories, categoryName -> {
            Fragment SelectedFragment = null;

            if (categoryName.equals("Telephone")) {
                SelectedFragment = new TelephoneFragment();
            }

            if ( SelectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, SelectedFragment)
                        .commit();
            }
        });
        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r.setAdapter(adapter);

        //gerons ici l'affichage des infos produits a ce stade

        ImageView productImage = findViewById(R.id.productImage);
        TextView nomProduit = findViewById(R.id.productName),
                descriptionProduit = findViewById(R.id.productDescription),
                quantiteProduit = findViewById(R.id.productQuantity),
                prixProduit = findViewById(R.id.productPrice);

        Button add = findViewById(R.id.buttonAdd),
                substract = findViewById(R.id.buttonSubstract);

        //on recupere nos donnees livres par l'intent
        Intent i = getIntent();
        if(i != null && i.hasExtra("produit")){
            Product p = (Product)i.getSerializableExtra("produit");
            if (p != null){
                productImage.setImageResource(p.getImage());
                nomProduit.setText(p.getNomImage());
                descriptionProduit.setText(p.getDescription());
                quantiteProduit.setText("Quantite: " + p.getQuantite() );
                prixProduit.setText("Prix: " + p.getPrixUnitaire());

            }
        }
        //gerons le comptage des produits sur le panier
        add.setOnClickListener(v->{cartItemCount++; updateCartBadge();});
        substract.setOnClickListener(v->{cartItemCount--;updateCartBadge();});


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
        MenuItem iconHome = menu.findItem(R.id.action_home);

        View actionView = menuItem.getActionView();

        if (actionView != null) { // Vérification ajoutée
            badgeTextView = actionView.findViewById(R.id.cart_badge);
            updateCartBadge();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_home) {
            // Lancer MainActivity (la page d'accueil)
          finish(); //  fermer DetailActivity et rentrer a l'activite preccedente
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //fonction pour gerer l'affichage du nombre de produit choisis
    private void updateCartBadge() {
        if (badgeTextView == null) return;

        if (cartItemCount == 0) {
            badgeTextView.setVisibility(View.GONE);
        } else {
            badgeTextView.setText(String.valueOf(cartItemCount));
            badgeTextView.setVisibility(View.VISIBLE);
        }
    }
}

