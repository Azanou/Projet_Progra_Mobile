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
    private int quantite;
    private double prixUnitaire;








    public Product(int image, int nomImage, int description, int quantite, double prixUnitaire) {
        this.image = image;
        this.nomImage = nomImage;
        this.description = description;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
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

