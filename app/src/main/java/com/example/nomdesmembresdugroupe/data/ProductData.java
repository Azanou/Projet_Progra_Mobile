package com.example.nomdesmembresdugroupe.data;
import com.example.nomdesmembresdugroupe.R;

import java.util.ArrayList;
import java.util.List;
public class ProductData {

    public static List<Product> getProducts() {
        List<Product> produits = new ArrayList<>();

        produits.add(new Product(R.drawable.wallhaven_q6x8md, R.string.produit1, R.string.description, 45, 29.99, R.string.Telephone));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit2, R.string.description, 20, 19.49, R.string.Telephone));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit3, R.string.description, 15, 89.99, R.string.Telephone));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit4, R.string.description, 30, 69.99, R.string.Accesiores_mobile));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit5, R.string.description, 100, 129.99, R.string.Accesiores_mobile));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit6, R.string.description, 50, 24.99, R.string.Accesiores_mobile));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit7, R.string.description, 75, 49.99, R.string.Appareils_audio));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit8, R.string.description, 60, 39.99, R.string.Appareils_audio));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit9, R.string.description, 90, 59.99, R.string.Appareils_audio));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit10, R.string.description, 30, 15.99, R.string.Batteries_externes));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit11, R.string.description, 50, 79.99, R.string.Batteries_externes));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit12, R.string.description, 25, 89.00, R.string.Batteries_externes));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit13, R.string.description, 55, 34.99, R.string.Chargeurs));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit15, R.string.description, 120, 19.99, R.string.Chargeurs));
        produits.add(new Product(R.drawable.ic_launcher_background, R.string.produit16, R.string.description, 80, 24.50, R.string.Chargeurs));
    return produits;
    }

}

