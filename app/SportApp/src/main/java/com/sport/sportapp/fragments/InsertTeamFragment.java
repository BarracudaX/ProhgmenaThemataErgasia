package com.sport.sportapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.sport.sportapp.R;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.TeamDao;
import domain.Athlete;
import domain.Sport;
import domain.SportIdNameModel;
import domain.Team;
import viewmodels.MainActivityViewModel;

public class InsertTeamFragment extends TeamFormFragment {

    @Override
    public int getButtonStringResourceId() {
        return R.string.team_create_button;
    }

    @Override
    public int getTitleStringResourceId() {
        return R.string.create_team_title;
    }

    @Override
    public void buttonClickedEvent(Team team) {
        viewModel.insertTeam(team);
        viewModel.navigateBack();
        Toast.makeText(getActivity(), R.string.added_team_success_message, Toast.LENGTH_LONG).show();
    }

}
