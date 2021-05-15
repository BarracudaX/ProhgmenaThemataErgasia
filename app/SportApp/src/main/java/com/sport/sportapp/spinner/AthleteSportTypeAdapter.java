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

import domain.Gender;
import domain.sport.AthleteSportType;

public class AthleteSportTypeAdapter extends ArrayAdapter<AthleteSportType> {

    public AthleteSportTypeAdapter(@NonNull Context context, List<AthleteSportType> athleteSportTypes) {
        super(context, 0,athleteSportTypes);
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
            AthleteSportType athleteSportType = getItem(position);
            SpinnerRowBinding binding = SpinnerRowBinding.inflate(LayoutInflater.from(getContext()),parent,false);
            binding.input.setText(athleteSportType.toString().replace("_"," ")+"("+athleteSportType.getNumberOfAthletes()+" athletes)");
            convertView = binding.getRoot();
        }
        return convertView;
    }
}
