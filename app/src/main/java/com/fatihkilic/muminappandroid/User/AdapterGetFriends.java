package com.fatihkilic.muminappandroid.User;



import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.GetFriendsRecyclerRowBinding;
import com.squareup.picasso.Picasso;

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


        Picasso.get().load(modelGetFriendsArrayList.get(position).friendsImage).into(holder.getFriendsRecyclerRowBinding.ppImageView);










        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent friendsDetailIntent = new Intent(holder.itemView.getContext(), FriendsDetailActivity.class);
                friendsDetailIntent.putExtra("FriendsInfo", "Friends");
                friendsDetailIntent.putExtra("FriendsEmail", modelGetFriendsArrayList.get(position).friendsEmail);
                holder.itemView.getContext().startActivity(friendsDetailIntent);

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
