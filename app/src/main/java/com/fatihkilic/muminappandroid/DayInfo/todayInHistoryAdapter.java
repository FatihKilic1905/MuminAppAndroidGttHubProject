package com.fatihkilic.muminappandroid.DayInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkilic.muminappandroid.databinding.TihRowBinding;

import java.util.ArrayList;

public class todayInHistoryAdapter extends RecyclerView.Adapter<todayInHistoryAdapter.TihHolder> {

    private ArrayList<todayInHistoryModel> tihArrayList;

    public todayInHistoryAdapter(ArrayList<todayInHistoryModel> tihArrayList) {
        this.tihArrayList = tihArrayList;
    }

    @NonNull
    @Override
    public TihHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TihRowBinding tihRowBinding = TihRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TihHolder(tihRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TihHolder holder, int position) {

        holder.tihRowBinding.tihDescriptionTextView.setText(tihArrayList.get(position).tihDescription);
        holder.tihRowBinding.tihIdTextView.setText(tihArrayList.get(position).tihYear);

    }

    @Override
    public int getItemCount() {
        return tihArrayList.size();
    }

    class TihHolder  extends RecyclerView.ViewHolder {

        TihRowBinding tihRowBinding;

        public TihHolder(TihRowBinding tihRowBinding) {
            super(tihRowBinding.getRoot());
            this.tihRowBinding = tihRowBinding;
        }
    }


}
