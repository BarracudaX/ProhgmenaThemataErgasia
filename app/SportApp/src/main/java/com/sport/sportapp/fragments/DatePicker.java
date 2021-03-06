package com.sport.sportapp.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Consumer;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private LocalDate localDatePicked;
    private final Consumer<LocalDate> pickedDateConsumer;

    public DatePicker(Consumer<LocalDate> pickedDateConsumer,LocalDate defaultValue) {
        this.pickedDateConsumer = pickedDateConsumer;
        this.localDatePicked = defaultValue;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        localDatePicked = LocalDate.of(year, month, dayOfMonth);
        pickedDateConsumer.accept(localDatePicked);
    }

    public LocalDate getLocalDatePicked(){
        return localDatePicked;
    }

    public void setLocalDatePicked(LocalDate localDate) {
        this.localDatePicked = localDate;
    }
}
