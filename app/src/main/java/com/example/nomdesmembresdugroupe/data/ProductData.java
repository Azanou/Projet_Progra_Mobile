package com.example.nomdesmembresdugroupe.data;
import com.example.nomdesmembresdugroupe.R;

import java.util.ArrayList;
import java.util.List;
public class ProductData {

        public static List<Product> getProducts() {
            List<Product> produits = new ArrayList<>();

            produits.add(new Product(R.drawable.ic_launcher_background, "produit1", "Description du produit 1", 0, 19.99));
            produits.add(new Product(R.drawable.ic_launcher_background, "produit2", "Description du produit 2", 0, 29.99));
            produits.add(new Product(R.drawable.ic_launcher_background, "produit3", "Description du produit 3", 0, 15.50));
            produits.add(new Product(R.drawable.ic_launcher_background, "produit4", "Description du produit 4", 0, 45.00));

            return produits;
        }
}
