package com.example.nomdesmembresdugroupe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nomdesmembresdugroupe.R;

public class TelephoneFragment extends Fragment {
    public TelephoneFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Lier à un layout XML
        return inflater.inflate(R.layout.fragment_telephone, container, false);
    }
}

