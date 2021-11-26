package com.fatihkilic.muminappandroid.DayInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.ActivityDayInfoBinding;
import com.fatihkilic.muminappandroid.databinding.MealRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class mealOfDayAdapter extends RecyclerView.Adapter<mealOfDayAdapter.MealHolder>{

    private ArrayList<mealOfDayModel> MealArrayLists;

    public mealOfDayAdapter(ArrayList<mealOfDayModel> mealArrayLists) {
        MealArrayLists = mealArrayLists;
    }

    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MealRowBinding mealRowBinding = MealRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MealHolder(mealRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {

        holder.mealRowBinding.eatNameTextView.setText(MealArrayLists.get(position).eatName);
        holder.mealRowBinding.eatMaterialsTextView.setText(MealArrayLists.get(position).eatMaterials);
        holder.mealRowBinding.eatpersonTextView.setText(MealArrayLists.get(position).eatPerson + "Kişi");
        holder.mealRowBinding.eatTimeTextView.setText(MealArrayLists.get(position).eatTime + "Dakika");
        holder.mealRowBinding.eatRecipeTextView.setText(MealArrayLists.get(position).eatRecipe);
        Picasso.get().load(MealArrayLists.get(position).eatImage).into(holder.mealRowBinding.eatImageView);

    }

    @Override
    public int getItemCount() {
        return MealArrayLists.size();
    }

    class MealHolder extends RecyclerView.ViewHolder {

        MealRowBinding mealRowBinding;

        public MealHolder(MealRowBinding mealRowBinding) {
            super(mealRowBinding.getRoot());
            this.mealRowBinding = mealRowBinding;
        }
    }

}
