package com.fatihkilic.muminappandroid.Kutuphane;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.RowKuranKerimKategoriBinding;

import java.util.ArrayList;

public class AdapterKuraniKerimKategori extends RecyclerView.Adapter<AdapterKuraniKerimKategori.KKKHolder> {

    private ArrayList<ModelKuraniKerimKategori> modelKuraniKerimKategoriArrayList;

    public AdapterKuraniKerimKategori(ArrayList<ModelKuraniKerimKategori> modelKuraniKerimKategoriArrayList) {
        this.modelKuraniKerimKategoriArrayList = modelKuraniKerimKategoriArrayList;
    }

    @NonNull
    @Override
    public KKKHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowKuranKerimKategoriBinding rowKuranKerimKategoriBinding = RowKuranKerimKategoriBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return  new KKKHolder(rowKuranKerimKategoriBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull KKKHolder holder, int position) {


        holder.rowKuranKerimKategoriBinding.suraNameTextView.setText(modelKuraniKerimKategoriArrayList.get(position).kategoriName);



    }

    @Override
    public int getItemCount() {
        return modelKuraniKerimKategoriArrayList.size();
    }

    class KKKHolder extends RecyclerView.ViewHolder {


        RowKuranKerimKategoriBinding rowKuranKerimKategoriBinding;

        public KKKHolder(RowKuranKerimKategoriBinding rowKuranKerimKategoriBinding) {
            super(rowKuranKerimKategoriBinding.getRoot());
            this.rowKuranKerimKategoriBinding = rowKuranKerimKategoriBinding;
        }
    }




}
