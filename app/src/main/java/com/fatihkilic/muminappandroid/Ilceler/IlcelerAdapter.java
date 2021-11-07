package com.fatihkilic.muminappandroid.Ilceler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.EzanVaktiCoplete.EzanVaktiActivity;
import com.fatihkilic.muminappandroid.MainActivity;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerActivity;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerAdapter;
import com.fatihkilic.muminappandroid.Sehirler.SehirlerPost;
import com.fatihkilic.muminappandroid.databinding.IlcelerRowBinding;
import com.fatihkilic.muminappandroid.databinding.SehirlerRowBinding;

import java.util.ArrayList;

public class IlcelerAdapter extends RecyclerView.Adapter<IlcelerAdapter.ilcelerHolder> {

    private ArrayList<IlcelerPost> ilcelerPostArrayList;

    public IlcelerAdapter(ArrayList<IlcelerPost> ilcelerPostArrayList) {

        this.ilcelerPostArrayList = ilcelerPostArrayList;
    }

    @NonNull
    @Override
    public IlcelerAdapter.ilcelerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        IlcelerRowBinding ilcelerRowBinding = IlcelerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new IlcelerAdapter.ilcelerHolder(ilcelerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ilcelerHolder holder, int position) {

        holder.binding.ilcelerTextView.setText(ilcelerPostArrayList.get(position).IlceAdi);
        String ilceIDString = ilcelerPostArrayList.get(position).IlceID;
        String ilcenameString = ilcelerPostArrayList.get(position).IlceAdi;



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent ilceIntent = new Intent(holder.itemView.getContext(), EzanVaktiActivity.class);
                ilceIntent.putExtra("ilceID",ilceIDString);
                ilceIntent.putExtra("ilceName",ilcenameString);
                holder.itemView.getContext().startActivity(ilceIntent);

            }
        });

    }




    @Override
    public int getItemCount() {
        return ilcelerPostArrayList.size();
    }



    public class ilcelerHolder extends RecyclerView.ViewHolder {

        private IlcelerRowBinding binding;

        public ilcelerHolder(IlcelerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
