package com.example.nomdesmembresdugroupe.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomdesmembresdugroupe.R;
import com.example.nomdesmembresdugroupe.data.CategoryProductAdapter;
import com.example.nomdesmembresdugroupe.data.Product;
import com.example.nomdesmembresdugroupe.data.ProductAdapter;
import com.example.nomdesmembresdugroupe.data.ProductData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView categoryList = view.findViewById(R.id.categoryList);
        categoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Product> categoryProducts = getProductByCategory(category);

        CategoryProductAdapter categoryProductsAdapter = new CategoryProductAdapter(categoryProducts);
        categoryList.setAdapter(categoryProductsAdapter);

        return view;
    }

    private List<Product> getProductByCategory(String categoryName){
        List<Product> allProduct = ProductData.getProducts();
        List<Product> result = new ArrayList<>();

        for(Product p : allProduct){
            String productCategory = getContext().getString(p.categorie);
            if(productCategory.equals(categoryName))
                result.add(p);
        }
        return result;
    }

}

