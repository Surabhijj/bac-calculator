/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/

package com.example.group10hw03;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewDrinkRecyclerViewAdapter extends RecyclerView.Adapter<ViewDrinkRecyclerViewAdapter.ViewDrinksViewHolder> {
    ArrayList<Drink> drinks = new ArrayList<>();

    public ViewDrinkRecyclerViewAdapter(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public ViewDrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_data_list,parent,false);
        ViewDrinksViewHolder viewDrinksViewHolder = new ViewDrinksViewHolder(view);
        return viewDrinksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDrinkRecyclerViewAdapter.ViewDrinksViewHolder holder, int position) {
        holder.drinkSize.setText(drinks.get(position).drinkSize.toString()+" oz");
        holder.drinkAlcoholNumber.setText(drinks.get(position).alcoholPercent.toString()+"% Alcohol");
        holder.timeTextView.setText("Added "+drinks.get(position).timestamp.toString());

        holder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinks.remove(drinks.get(holder.getAdapterPosition()));
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(),getItemCount());
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.drinks.size();
    }

    public static class ViewDrinksViewHolder extends RecyclerView.ViewHolder {
        TextView drinkSize;
        TextView drinkAlcoholNumber;
        TextView timeTextView ;
        View rootView;
        ImageButton deletebutton;

        public ViewDrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            drinkSize = itemView.findViewById(R.id.drinkSizeTextView);
            drinkAlcoholNumber = itemView.findViewById(R.id.ozValueTextView);
            timeTextView = itemView.findViewById(R.id.dateTimeTextView);
            deletebutton = itemView.findViewById(R.id.deleteDrinkButton);


        }
    }
}
