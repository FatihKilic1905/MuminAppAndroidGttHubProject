package com.fatihkilic.muminappandroid.Kutuphane;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.KuraniKerimRowBinding;

import java.util.ArrayList;

public class AdapterKuraniKerimPage extends RecyclerView.Adapter<AdapterKuraniKerimPage.KuraniKerimHolder> {


    private ArrayList<ModelKuranıKerimPageArapca> modelKuranıKerimPageArapcaArrayList;
    private ArrayList<ModelKraniKerimPageMeal> modelKraniKerimPageMealArrayList;

    public AdapterKuraniKerimPage(ArrayList<ModelKuranıKerimPageArapca> modelKuranıKerimPageArapcaArrayList, ArrayList<ModelKraniKerimPageMeal> modelKraniKerimPageMealArrayList) {
        this.modelKuranıKerimPageArapcaArrayList = modelKuranıKerimPageArapcaArrayList;
        this.modelKraniKerimPageMealArrayList = modelKraniKerimPageMealArrayList;
    }

    @NonNull
    @Override
    public KuraniKerimHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KuraniKerimRowBinding kuraniKerimRowBinding = KuraniKerimRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new KuraniKerimHolder(kuraniKerimRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull KuraniKerimHolder holder, int position) {

        holder.kuraniKerimRowBinding.arapcaTextView.setText(modelKuranıKerimPageArapcaArrayList.get(position).textArapPage);
        holder.kuraniKerimRowBinding.turkceTextView.setText(modelKraniKerimPageMealArrayList.get(position).textMeal);





        StringBuilder juzText = new StringBuilder();
        juzText.append(modelKuranıKerimPageArapcaArrayList.get(position).juzArapPage);
        juzText.append(".");
        juzText.append(" Cüz");

        holder.kuraniKerimRowBinding.juzTextView.setText(juzText.toString());


        StringBuilder pageText = new StringBuilder();
        pageText.append(modelKuranıKerimPageArapcaArrayList.get(position).pageArapPage);
        pageText.append(".");
        pageText.append(" Sayfa");

        holder.kuraniKerimRowBinding.pageTextView.setText(pageText.toString());



        StringBuilder ayetText = new StringBuilder();
        ayetText.append(modelKuranıKerimPageArapcaArrayList.get(position).numberInSurahArapPage);
        ayetText.append(".");
        ayetText.append(" Ayet");

        if (modelKuranıKerimPageArapcaArrayList.get(position).sajdaArapPage == 0) {

            holder.kuraniKerimRowBinding.ayetTextView.setText(ayetText.toString());


        } else if (modelKuranıKerimPageArapcaArrayList.get(position).sajdaArapPage == 1) {


            holder.kuraniKerimRowBinding.ayetTextView.setText("Secde");


        }



    }

    @Override
    public int getItemCount() {
        return modelKuranıKerimPageArapcaArrayList.size();
    }

    class KuraniKerimHolder extends RecyclerView.ViewHolder {

        KuraniKerimRowBinding kuraniKerimRowBinding;

        public KuraniKerimHolder(KuraniKerimRowBinding kuraniKerimRowBinding) {
            super(kuraniKerimRowBinding.getRoot());
            this.kuraniKerimRowBinding = kuraniKerimRowBinding;
        }
    }


}
