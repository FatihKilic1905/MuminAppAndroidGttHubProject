package com.fatihkilic.muminappandroid.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.GetFriendsRecyclerRowBinding;

import java.util.ArrayList;

public class  AdapterGetFriends extends RecyclerView.Adapter<AdapterGetFriends.ModelGetFriendsHolder>{

    private ArrayList<ModelGetFriends> modelGetFriendsArrayList;

    public AdapterGetFriends(ArrayList<ModelGetFriends> modelGetFriendsArrayList) {
        this.modelGetFriendsArrayList = modelGetFriendsArrayList;
    }

    @NonNull
    @Override
    public ModelGetFriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GetFriendsRecyclerRowBinding getFriendsRecyclerRowBinding = GetFriendsRecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ModelGetFriendsHolder(getFriendsRecyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelGetFriendsHolder holder, int position) {

        holder.getFriendsRecyclerRowBinding.userNameTextView.setText(modelGetFriendsArrayList.get(position).frienduserName);

        String name = modelGetFriendsArrayList.get(position).frinedsName;
        String surName = modelGetFriendsArrayList.get(position).friendsSurName;

        StringBuilder ns = new StringBuilder();
        ns.append(name);
        ns.append(" ");
        ns.append(surName);

        holder.getFriendsRecyclerRowBinding.nameSurnameTextView.setText(ns.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println(modelGetFriendsArrayList.get(position).friendsEmail);

            }
        });







    }

    @Override
    public int getItemCount() {
        return modelGetFriendsArrayList.size();
    }

    class ModelGetFriendsHolder extends RecyclerView.ViewHolder {

        GetFriendsRecyclerRowBinding getFriendsRecyclerRowBinding;


        public ModelGetFriendsHolder(GetFriendsRecyclerRowBinding getFriendsRecyclerRowBinding) {
            super(getFriendsRecyclerRowBinding.getRoot());

            this.getFriendsRecyclerRowBinding = getFriendsRecyclerRowBinding;
        }
    }

}
