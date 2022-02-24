package com.fatihkilic.muminappandroid.ZikirMatik;



import static com.fatihkilic.muminappandroid.ZikirMatik.KisilerActivity.toVcKisilerStaticNew;
import static com.fatihkilic.muminappandroid.ZikirMatik.KisilerActivity.zikirNewEmailArrayList;
import static com.fatihkilic.muminappandroid.ZikirMatik.KisilerActivity.zikirNewUserArraylist;
import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirAddActivity.kiekleEmailArrayList;
import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirAddActivity.kisiEkleArrayList;
import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirUsersActivity.kisiEkleEmailUserArrayList;
import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirUsersActivity.kisiEkleUserArrayList;
import static com.fatihkilic.muminappandroid.ZikirMatik.ZikirUsersActivity.toVcKisilerStatic;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.KisilerRowBinding;

import java.util.ArrayList;

public class AdapterKisiler extends RecyclerView.Adapter<AdapterKisiler.KisilerHolder> {


    private ArrayList<ModelKisiler> modelKisilerArrayList;

    public AdapterKisiler(ArrayList<ModelKisiler> modelKisilerArrayList) {
        this.modelKisilerArrayList = modelKisilerArrayList;
    }

    @NonNull
    @Override
    public KisilerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KisilerRowBinding kisilerRowBinding = KisilerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return  new KisilerHolder(kisilerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull KisilerHolder holder, int position) {


        if (toVcKisilerStaticNew.equals("ZikirAddPage")) {

            holder.kisilerRowBinding.nameTextView.setText(modelKisilerArrayList.get(position).frienduserName);

            if (kisiEkleArrayList.contains(modelKisilerArrayList.get(position).frienduserName)) {

                holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(100, 43, 45));


            } else  {

                holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(255, 255, 255));

            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (kisiEkleArrayList.contains(modelKisilerArrayList.get(position).frienduserName)) {


                        holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(255, 255, 255));

                        kisiEkleArrayList.remove(modelKisilerArrayList.get(position).frienduserName);
                        kiekleEmailArrayList.remove(modelKisilerArrayList.get(position).friendsEmail);

                        System.out.println(kisiEkleArrayList);


                    } else {

                        holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(100, 43, 45));

                        kisiEkleArrayList.add(modelKisilerArrayList.get(position).frienduserName);
                        kiekleEmailArrayList.add(modelKisilerArrayList.get(position).friendsEmail);

                        System.out.println(kisiEkleArrayList);

                    }


                }
            });


        } else if (toVcKisilerStaticNew.equals("ZikirUsersPage")) {




            holder.kisilerRowBinding.nameTextView.setText(modelKisilerArrayList.get(position).frienduserName);

            if (kisiEkleUserArrayList.contains(modelKisilerArrayList.get(position).frienduserName)) {

                holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(100,43,45));


            } else {

                holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(255,255,255));

            }


            if (kisiEkleUserArrayList.contains(modelKisilerArrayList.get(position).frienduserName)) {


                holder.itemView.setEnabled(false);


            }




            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {







                    if (zikirNewUserArraylist.contains(modelKisilerArrayList.get(position).frienduserName)) {




                        holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(255,255,255));

                        zikirNewUserArraylist.remove(modelKisilerArrayList.get(position).frienduserName);
                        zikirNewEmailArrayList.remove(modelKisilerArrayList.get(position).friendsEmail);


                        System.out.println(zikirNewUserArraylist);
                        System.out.println(zikirNewEmailArrayList);




                    } else {



                        zikirNewUserArraylist.add(modelKisilerArrayList.get(position).frienduserName);
                        zikirNewEmailArrayList.add(modelKisilerArrayList.get(position).friendsEmail);
                        holder.kisilerRowBinding.nameCheckBox.setBackgroundColor(Color.rgb(0,0,255));


                        System.out.println(zikirNewUserArraylist);
                        System.out.println(zikirNewEmailArrayList);




                    }


                }
            });





        }



    }

    @Override
    public int getItemCount() {
        return modelKisilerArrayList.size();
    }

    class KisilerHolder extends RecyclerView.ViewHolder {

        KisilerRowBinding kisilerRowBinding;


        public KisilerHolder(KisilerRowBinding kisilerRowBinding) {
            super(kisilerRowBinding.getRoot());

            this.kisilerRowBinding = kisilerRowBinding;

        }
    }
}
