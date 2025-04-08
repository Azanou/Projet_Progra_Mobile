package com.example.nomdesmembresdugroupe.data;
import android.widget.Toast;

import java.util.HashMap;

public class UserCredentials {
        public static final HashMap<String, String> USER_CREDENTIALS = new HashMap<>();

        // Méthode statique pour ajouter un nouvel utilisateur
        public static void addUserCredentials(String password, String username) {
            // Vérifie si l'utilisateur existe déjà
            if (!USER_CREDENTIALS.containsKey(password)) {
                USER_CREDENTIALS.put(password, username);
                System.out.println("Utilisateur ajouté : " + username);
            } else {
                System.out.println("L'utilisateur existe déjà : " + username);
            }
        }
        public static void displayUsers() {
            for (String username : USER_CREDENTIALS.keySet()) {
                System.out.println("Utilisateur : " + username);
            }
        }
}