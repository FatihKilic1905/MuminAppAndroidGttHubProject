package com.fatihkilic.muminappandroid.Sehirler;

import android.content.Intent;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.Ilceler.IlcelerActivity;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.Ulkeler.UlkeAdapter;
import com.fatihkilic.muminappandroid.Ulkeler.UlkelerPost;
import com.fatihkilic.muminappandroid.databinding.SehirlerRowBinding;
import com.fatihkilic.muminappandroid.databinding.UlkelerRowBinding;

import java.util.ArrayList;

public class SehirlerAdapter extends RecyclerView.Adapter<SehirlerAdapter.sehirlerHolder> {

    private ArrayList<SehirlerPost> sehirlerPostArrayList;

    public SehirlerAdapter(ArrayList<SehirlerPost> sehirlerPostArrayList) {

        this.sehirlerPostArrayList = sehirlerPostArrayList;
    }

    @NonNull
    @Override
    public sehirlerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SehirlerRowBinding sehirlerRowBinding = SehirlerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new sehirlerHolder(sehirlerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull sehirlerHolder holder, int position) {

        holder.binding.sehirlerTextView.setText(sehirlerPostArrayList.get(position).SehirAdi);
        String sehirIDString = sehirlerPostArrayList.get(position).SehirID;



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sehirIntent = new Intent(holder.itemView.getContext(), IlcelerActivity.class);
                sehirIntent.putExtra("sehirID",sehirIDString);
                holder.itemView.getContext().startActivity(sehirIntent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return sehirlerPostArrayList.size();
    }

    public class sehirlerHolder extends RecyclerView.ViewHolder {

        private SehirlerRowBinding binding;

        public sehirlerHolder(SehirlerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
