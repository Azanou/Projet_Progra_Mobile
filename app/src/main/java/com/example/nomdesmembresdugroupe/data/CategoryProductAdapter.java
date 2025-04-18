package com.example.nomdesmembresdugroupe.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomdesmembresdugroupe.DetailActivity;
import com.example.nomdesmembresdugroupe.R;

import java.util.List;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.MyViewHolder>{

    List<Product> products ;

    public CategoryProductAdapter(List<Product> listeProduits) {
        this.products = listeProduits;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView name;
        public MyViewHolder(View itemview){
            super(itemview);
            image = itemview.findViewById(R.id.image_category);
            name = itemview.findViewById(R.id.text_p_category);
        }
    }

    @NonNull
    @Override
    public CategoryProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_category, parent, false);
        return new CategoryProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product itemElement = this.products.get(position);
        holder.image.setImageResource(itemElement.getImage());
        holder.name.setText(itemElement.getNomImage());

        holder.name.setOnClickListener(v -> {
            Context C = v.getContext();
            Intent i = new Intent(C, DetailActivity.class);
            i.putExtra("produit", itemElement);
            C.startActivity(i);
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}
