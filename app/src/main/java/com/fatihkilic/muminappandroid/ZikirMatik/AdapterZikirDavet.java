package com.fatihkilic.muminappandroid.ZikirMatik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.ZikirDavetRowBinding;
import com.fatihkilic.muminappandroid.databinding.ZikirlerimRowBinding;

import java.util.ArrayList;

public class AdapterZikirDavet extends RecyclerView.Adapter<AdapterZikirDavet.ZikirDavetHolder> {

    ArrayList<ModelZikirDAvet> modelZikirDAvetArrayList;

    public AdapterZikirDavet(ArrayList<ModelZikirDAvet> modelZikirDAvetArrayList) {
        this.modelZikirDAvetArrayList = modelZikirDAvetArrayList;
    }

    @NonNull
    @Override
    public ZikirDavetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ZikirDavetRowBinding zikirDavetRowBinding = ZikirDavetRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new AdapterZikirDavet.ZikirDavetHolder(zikirDavetRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZikirDavetHolder holder, int position) {

        StringBuilder davet = new StringBuilder();
        davet.append(modelZikirDAvetArrayList.get(position).nickNameDavet);
        davet.append(" ");
        davet.append("sizi");
        davet.append(" ");
        davet.append(modelZikirDAvetArrayList.get(position).zikirNameDavet);
        davet.append(" ");
        davet.append("zikretmeye davet ediyor");

        holder.zikirDavetRowBinding.davetTextView.setText(davet.toString());



    }

    @Override
    public int getItemCount() {
        return modelZikirDAvetArrayList.size();
    }

    class ZikirDavetHolder extends RecyclerView.ViewHolder {

        ZikirDavetRowBinding zikirDavetRowBinding;

            public ZikirDavetHolder(ZikirDavetRowBinding zikirDavetRowBinding) {
                super(zikirDavetRowBinding.getRoot());

                this.zikirDavetRowBinding = zikirDavetRowBinding;
            }
        }



}
