package com.example.nomdesmembresdugroupe.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    @DrawableRes
        private int image;
    @StringRes
        private int nomImage;
    @StringRes
        private int description;
    @StringRes
        private int quantite;
        private double prixUnitaire;
    @StringRes
        public int categorie;







        public Product(int image, int nomImage, int description, int quantite, double prixUnitaire, int categorie) {
            this.image = image;
            this.nomImage = nomImage;
            this.description = description;
            this.quantite = quantite;
            this.prixUnitaire = prixUnitaire;
            this.categorie = categorie;
        }

        public int getImage() {
            return image;
        }
        @StringRes
        public int getNomImage() {
            return nomImage;
        }

        @StringRes
        public int getDescription() { return description; }

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

