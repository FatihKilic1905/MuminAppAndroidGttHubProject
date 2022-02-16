package com.fatihkilic.muminappandroid.ZikirMatik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.ZikirlerimRowBinding;

import java.util.ArrayList;

public class AdapterZikirlerim extends RecyclerView.Adapter<AdapterZikirlerim.ZikirlerimHolder> {


    private ArrayList<ModelZikirlerim> modelZikirlerimArrayList;

    public AdapterZikirlerim(ArrayList<ModelZikirlerim> modelZikirlerimArrayList) {
        this.modelZikirlerimArrayList = modelZikirlerimArrayList;
    }

    @NonNull
    @Override
    public ZikirlerimHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ZikirlerimRowBinding zikirlerimRowBinding = ZikirlerimRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ZikirlerimHolder(zikirlerimRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ZikirlerimHolder holder, int position) {

        String zikirCompCount = String.valueOf(modelZikirlerimArrayList.get(position).zikirCount);

        holder.zikirlerimRowBinding.myZikirZikirName.setText(modelZikirlerimArrayList.get(position).zikirName);
        holder.zikirlerimRowBinding.myZikirzikirCount.setText(zikirCompCount);
        holder.zikirlerimRowBinding.myZikirEndDate.setText(modelZikirlerimArrayList.get(position).endDateMyzikir);
        holder.zikirlerimRowBinding.zikirlerimProgresBar.setMax(modelZikirlerimArrayList.get(position).zikirCount);
        holder.zikirlerimRowBinding.zikirlerimProgresBar.setProgress(modelZikirlerimArrayList.get(position).zikirCompleteCount);
        holder.zikirlerimRowBinding.zikirlerimProgresBarText.setText(String.valueOf(modelZikirlerimArrayList.get(position).zikirCompleteCount));

    }

    @Override
    public int getItemCount() {
        return modelZikirlerimArrayList.size();
    }

    public class ZikirlerimHolder extends RecyclerView.ViewHolder{

        ZikirlerimRowBinding zikirlerimRowBinding;

        public ZikirlerimHolder(ZikirlerimRowBinding zikirlerimRowBinding) {
            super(zikirlerimRowBinding.getRoot());

            this.zikirlerimRowBinding = zikirlerimRowBinding;
        }
    }


}
