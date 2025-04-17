package com.example.nomdesmembresdugroupe.data;

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

    public CategoryAdapter(List<Integer> cat){
        this.categoryTab = cat;
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
        holder.category.setText(category);
    }

    @Override
    public int getItemCount() {
        return categoryTab.size();
    }
}
