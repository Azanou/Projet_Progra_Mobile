package com.example.nomdesmembresdugroupe.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//ici est les listes des utilisateurs et des produits que nous utiliserons
//aussi bien pour afficher nos produits d'une part et d'autre part pour authentifier nos utilisateurs


public record Data() {

    static HashMap<String, String> df = new HashMap<String, String>();
    static List<Product> products = Arrays.asList();
}
