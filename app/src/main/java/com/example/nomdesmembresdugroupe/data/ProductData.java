package com.example.nomdesmembresdugroupe.data;

public class ProductData {

       package com.example.yourapp.data;

import java.util.ArrayList;
import java.util.List;

    public class ProductData {

        public static List<Product> getProducts() {
            List<Product> produits = new ArrayList<>();

            produits.add(new Product(R.drawable.produit1, "produit1", "Description du produit 1", 0, 19.99));
            produits.add(new Product(R.drawable.produit2, "produit2", "Description du produit 2", 0, 29.99));
            produits.add(new Product(R.drawable.produit3, "produit3", "Description du produit 3", 0, 15.50));
            produits.add(new Product(R.drawable.produit4, "produit4", "Description du produit 4", 0, 45.00));

            return produits;
        }
    }
}
