package com.sport.sportapp.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sport.sportapp.databinding.SpinnerRowBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import domain.sport.AthleteSport;
import domain.sport.AthleteSportType;

public class AthleteSportAdapter extends ArrayAdapter<AthleteSport> {

    public AthleteSportAdapter(@NonNull Context context, List<AthleteSport> athleteSports) {
        super(context, 0,athleteSports);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            AthleteSport athleteSport = getItem(position);
            SpinnerRowBinding binding = SpinnerRowBinding.inflate(LayoutInflater.from(getContext()),parent,false);
            binding.input.setText(athleteSport.getSportName()+"("+athleteSport.getAthleteSportType().toString().replace("_"," ")+")");
            convertView = binding.getRoot();
        }
        return convertView;
    }
}
