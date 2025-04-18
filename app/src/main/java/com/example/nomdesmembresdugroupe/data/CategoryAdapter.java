package com.example.nomdesmembresdugroupe.data;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.nomdesmembresdugroupe.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    List<Integer> categoryTab;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private  OnCategoryClickListener listener;


    //interface (on y fera un callback) pour gerer les clics sur les categories
    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryName);
    }

    public CategoryAdapter(List<Integer> cat, OnCategoryClickListener listener){
        this.categoryTab = cat;
        this.listener = listener;
    }
    public static class MyViewHolder extends ViewHolder{

        public TextView category;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.category = itemView.findViewById(R.id.categoryText);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        int category = this.categoryTab.get(position);
        String categoryText = holder.itemView.getContext().getString(category);

        holder.category.setText(category);

        // Mise à jour du style selon la sélection
        if (position == selectedPosition) {
            holder.category.setBackgroundResource(R.drawable.bg_selected);
            holder.category.setTextColor(Color.WHITE);
        } else {
            holder.category.setBackgroundResource(R.drawable.bg_unselected);
            holder.category.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(v -> {

            int previousPosition = selectedPosition;
            selectedPosition = position;

            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onCategoryClick(categoryText); // ← ici on envoie le texte réel (ex: "Téléphone")
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryTab.size();
    }
}
