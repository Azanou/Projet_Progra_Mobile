package com.example.nomdesmembresdugroupe.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomdesmembresdugroupe.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    List<Product> products ;

    public ProductAdapter(List<Product> listeProduits) {
        this.products = listeProduits;
    }


    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView description;
        public TextView unitPrice;
        public TextView numberOfItem;
        public MyViewHolder(View itemview){
            super(itemview);
            image = itemview.findViewById(R.id.image);
            name = itemview.findViewById(R.id.nomImage);
            description = itemview.findViewById(R.id.description);
            unitPrice = itemview.findViewById(R.id.prixUnitaire);
            numberOfItem = itemview.findViewById(R.id.quantite);
        }
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product itemElement = this.products.get(position);
        holder.image.setImageResource(itemElement.getImage());
        holder.name.setText(itemElement.getNomImage());
        holder.description.setText(itemElement.getDescription());
        holder.unitPrice.setText(String.valueOf(itemElement.getPrixUnitaire()));
        holder.numberOfItem.setText(String.valueOf(itemElement.getQuantite()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
