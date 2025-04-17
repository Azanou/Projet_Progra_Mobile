package com.example.nomdesmembresdugroupe.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nomdesmembresdugroupe.R;

public class CategoryFragment extends Fragment {

    String category;
    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(String category) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FRAGMENT", "Fragment affiché !");

        if (getArguments() != null) {
            category = getArguments().getString("category");
        }


        // Lier à un layout XML
        switch (category){
            case "Telephone":
                return inflater.inflate(R.layout.fragment_telephone, container, false);
            case "Accessoires mobiles":
                return inflater.inflate(R.layout.fragment_accesmo, container, false);
            case "Appareils audio":
                return inflater.inflate(R.layout.fragment_audio, container, false);
            case "Batteries externes":
                return inflater.inflate(R.layout.fragment_batext, container, false);
            case "Casques":
                return inflater.inflate(R.layout.fragment_casque, container, false);
            case "Chargeurs" :
                return inflater.inflate(R.layout.fragment_chargeur, container, false);
            default:
                return inflater.inflate(R.layout.fragment_telephone, container, false);
        }
    }
}

