package com.fatihkilic.muminappandroid.Ulkeler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.Sehirler.SehirlerActivity;
import com.fatihkilic.muminappandroid.databinding.UlkelerRowBinding;

import java.util.ArrayList;

public class UlkeAdapter extends RecyclerView.Adapter<UlkeAdapter.UlkePostHolder> {

    private ArrayList<UlkelerPost> ulkelerPostArrayList;



    public UlkeAdapter(ArrayList<UlkelerPost> ulkelerPostArrayList) {

        this.ulkelerPostArrayList = ulkelerPostArrayList;


    }
    @NonNull
    @Override
    public UlkePostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UlkelerRowBinding ulkelerRowBinding = UlkelerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new UlkePostHolder(ulkelerRowBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull UlkePostHolder holder, int position) {

        holder.ulkelerRowBinding.ulkelerTextView.setText(ulkelerPostArrayList.get(position).UlkeAdi);
        String sehirIDString = ulkelerPostArrayList.get(position).UlkeID;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent ulkeIntent = new Intent(holder.itemView.getContext(), SehirlerActivity.class);
                ulkeIntent.putExtra("ulkeID",sehirIDString);
                holder.itemView.getContext().startActivity(ulkeIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return ulkelerPostArrayList.size();
    }

    class UlkePostHolder extends RecyclerView.ViewHolder {

        UlkelerRowBinding ulkelerRowBinding;

        public UlkePostHolder(UlkelerRowBinding ulkelerRowBinding) {
            super(ulkelerRowBinding.getRoot());
            this.ulkelerRowBinding = ulkelerRowBinding;
        }
    }

}
