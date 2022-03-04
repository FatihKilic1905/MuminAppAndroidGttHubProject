package com.fatihkilic.muminappandroid.User;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.GetFriendsRecyclerRowBinding;
import com.fatihkilic.muminappandroid.databinding.GetFriendsRequestRecyclerRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterGetFriendsRequest extends RecyclerView.Adapter<AdapterGetFriendsRequest.FriendsRequestHolder> {

    public AdapterGetFriendsRequest(ArrayList<ModelGetFriendsRequest> modelGetFriendsRequestArrayList) {
        this.modelGetFriendsRequestArrayList = modelGetFriendsRequestArrayList;
    }

    private ArrayList<ModelGetFriendsRequest> modelGetFriendsRequestArrayList;

    @NonNull
    @Override
    public FriendsRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GetFriendsRequestRecyclerRowBinding getFriendsRequestRecyclerRowBinding = GetFriendsRequestRecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FriendsRequestHolder(getFriendsRequestRecyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsRequestHolder holder, int position) {

        holder.getFriendsRequestRecyclerRowBinding.userNameTextView.setText(modelGetFriendsRequestArrayList.get(position).frienduserName);

        String name = modelGetFriendsRequestArrayList.get(position).frinedsName;
        String surName = modelGetFriendsRequestArrayList.get(position).friendsSurName;


        StringBuilder ns = new StringBuilder();
        ns.append(name);
        ns.append(" ");
        ns.append(surName);

        holder.getFriendsRequestRecyclerRowBinding.nameSurnameTextView.setText(ns.toString());

        Picasso.get().load(modelGetFriendsRequestArrayList.get(position).friendsImage).into(holder.getFriendsRequestRecyclerRowBinding.ppImageView);







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent friendsDetailIntent = new Intent(holder.itemView.getContext(), FriendsDetailActivity.class);
                friendsDetailIntent.putExtra("FriendsInfo", "FriendsRequest");
                friendsDetailIntent.putExtra("FriendsEmail", modelGetFriendsRequestArrayList.get(position).friendsEmail);
                holder.itemView.getContext().startActivity(friendsDetailIntent);




            }
        });



    }

    @Override
    public int getItemCount() {
        return modelGetFriendsRequestArrayList.size();
    }

    class FriendsRequestHolder extends RecyclerView.ViewHolder {

         GetFriendsRequestRecyclerRowBinding getFriendsRequestRecyclerRowBinding;


        public FriendsRequestHolder(@NonNull GetFriendsRequestRecyclerRowBinding getFriendsRequestRecyclerRowBinding) {
            super(getFriendsRequestRecyclerRowBinding.getRoot());

            this.getFriendsRequestRecyclerRowBinding = getFriendsRequestRecyclerRowBinding;

        }
    }





}
