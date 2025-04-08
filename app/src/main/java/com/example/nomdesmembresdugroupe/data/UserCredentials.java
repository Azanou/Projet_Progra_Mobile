package com.example.nomdesmembresdugroupe.data;
import java.util.HashMap;

public class UserCredentials {
        private static final HashMap<String, String> USER_CREDENTIALS = new HashMap<>();

        // Méthode statique pour ajouter un nouvel utilisateur
        public static void addUserCredentials(String username, String password) {
            // Vérifie si l'utilisateur existe déjà
            if (!USER_CREDENTIALS.containsKey(username)) {
                USER_CREDENTIALS.put(username, password);
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