package com.example.nomdesmembresdugroupe.data;

public class Product {

        private int image;
        private String nomImage;
        private String description;
        private int quantite;
        private double prixUnitaire;

        public Product(int image, String nomImage, String description, int quantite, double prixUnitaire) {
            this.image = image;
            this.nomImage = nomImage;
            this.description = description;
            this.quantite = quantite;
            this.prixUnitaire = prixUnitaire;
        }

        public int getImage() {
            return image;
        }

        public String getNomImage() {
            return nomImage;
        }

        public String getDescription() {
            return description;
        }

        public int getQuantite() {
            return quantite;
        }

        public double getPrixUnitaire() {
            return prixUnitaire;
        }

        public void setQuantitePlus() {
            this.quantite++;
        }

        public void setQuantiteLess() {
            if (this.quantite > 0) {
                this.quantite--;
            }
        }
}

