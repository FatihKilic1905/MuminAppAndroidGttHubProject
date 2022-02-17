package com.fatihkilic.muminappandroid.ZikirMatik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.IstirakRowBinding;
import com.fatihkilic.muminappandroid.databinding.ZikirlerimRowBinding;

import java.util.ArrayList;

public class AdapterIstirak extends RecyclerView.Adapter<AdapterIstirak.IstirakHolder> {

    private ArrayList<ModelZikirIstirak> modelZikirIstirakArrayList;

    public AdapterIstirak(ArrayList<ModelZikirIstirak> modelZikirIstirakArrayList) {
        this.modelZikirIstirakArrayList = modelZikirIstirakArrayList;
    }

    @NonNull
    @Override
    public IstirakHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IstirakRowBinding istirakRowBinding = IstirakRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new IstirakHolder(istirakRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IstirakHolder holder, int position) {



        holder.istirakRowBinding.userZikirNameTextView.setText(modelZikirIstirakArrayList.get(position).zikirNameIstirak);
        holder.istirakRowBinding.userZikirCountTextView.setText(modelZikirIstirakArrayList.get(position).zikirCountIstirak);
        holder.istirakRowBinding.userZikirOwner.setText(modelZikirIstirakArrayList.get(position).nickNameIstirak);
        holder.istirakRowBinding.userZikirEndDate.setText(modelZikirIstirakArrayList.get(position).endDateIstirak);
        holder.istirakRowBinding.istirakProgresBar.setMax(modelZikirIstirakArrayList.get(position).zikirMyCount);
        holder.istirakRowBinding.istirakProgresBar.setProgress(modelZikirIstirakArrayList.get(position).zikirMyCompleteCount);
        holder.istirakRowBinding.istirakProgresBarText.setText(String.valueOf(modelZikirIstirakArrayList.get(position).zikirMyCompleteCount));





    }

    @Override
    public int getItemCount() {
        return modelZikirIstirakArrayList.size();
    }

    class IstirakHolder extends RecyclerView.ViewHolder {

        IstirakRowBinding istirakRowBinding;

        public IstirakHolder(IstirakRowBinding istirakRowBinding) {
            super(istirakRowBinding.getRoot());

            this.istirakRowBinding = istirakRowBinding;
        }
    }


}
