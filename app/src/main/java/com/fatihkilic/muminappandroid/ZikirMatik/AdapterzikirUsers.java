package com.fatihkilic.muminappandroid.ZikirMatik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.ZikirUsersRowBinding;

import java.util.ArrayList;

class AdapterZikirUsers extends RecyclerView.Adapter<AdapterZikirUsers.ZikirUsersHolder> {

    private ArrayList<ModelZikirUsers> modelZikirUsersArrayList;

    public AdapterZikirUsers(ArrayList<ModelZikirUsers> modelZikirUsersArrayList) {
        this.modelZikirUsersArrayList = modelZikirUsersArrayList;
    }

    @NonNull
    @Override
    public ZikirUsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ZikirUsersRowBinding zikirUsersRowBinding = ZikirUsersRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ZikirUsersHolder(zikirUsersRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZikirUsersHolder holder, int position) {

        holder.zikirUsersRowBinding.userNameTextView.setText(modelZikirUsersArrayList.get(position).userName);
        holder.zikirUsersRowBinding.myCountTextview.setText(String.valueOf(modelZikirUsersArrayList.get(position).totalCount));
        holder.zikirUsersRowBinding.usersCountProgresBar.setMax(modelZikirUsersArrayList.get(position).totalCount);
        holder.zikirUsersRowBinding.usersCountProgresBar.setProgress(modelZikirUsersArrayList.get(position).zikirCount);
        holder.zikirUsersRowBinding.zikirUserProgresBarText.setText(String.valueOf(modelZikirUsersArrayList.get(position).zikirCount));



    }

    @Override
    public int getItemCount() {
        return modelZikirUsersArrayList.size();
    }

    public class ZikirUsersHolder extends RecyclerView.ViewHolder {


        ZikirUsersRowBinding zikirUsersRowBinding;

        public ZikirUsersHolder(ZikirUsersRowBinding zikirUsersRowBinding) {
            super(zikirUsersRowBinding.getRoot());

            this.zikirUsersRowBinding = zikirUsersRowBinding;
        }
    }





}
