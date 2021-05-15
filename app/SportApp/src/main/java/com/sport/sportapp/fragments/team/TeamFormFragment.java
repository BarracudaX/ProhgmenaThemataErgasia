package com.sport.sportapp.fragments.team;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentTeamFormBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;
import com.sport.sportapp.spinner.SportTeamAdapter;

import java.time.LocalDate;

import domain.sport.TeamSport;
import domain.team.Team;
import viewmodels.SportViewModel;
import viewmodels.TeamViewModel;

public abstract class TeamFormFragment extends BaseFragment {

    protected FragmentTeamFormBinding binding;
    protected TeamViewModel teamViewModel;
    protected SportViewModel sportViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
        sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);
        binding = FragmentTeamFormBinding.inflate(inflater,container,false);
        DatePicker datePicker = new DatePicker(localDate -> binding.teamFoundationInput.setText(localDate.toString()),LocalDate.now());

        binding.teamFoundationInput.setOnClickListener(v -> {
             datePicker.show(getChildFragmentManager(),"datePicker");
        });

        sportViewModel.teamSports().observe(getViewLifecycleOwner(), sportTeams -> {
            binding.teamSportInput.setAdapter(new SportTeamAdapter(getContext(),sportTeams));
        });

        binding.title.setText(getTitleStringResourceId());
        binding.teamButton.setText(getButtonStringResourceId());
        binding.teamButton.setOnClickListener((v)->{
            TeamSport teamSport = ((TeamSport) binding.teamSportInput.getSelectedItem());
            buttonClickedEvent(new Team(
                    binding.teamNameInput.getText().toString(),
                    binding.stadiumNameInput.getText().toString(),
                    binding.teamCityInput.getText().toString(),
                    binding.teamCountryInput.getText().toString(),
                    datePicker.getLocalDatePicked(),
                    teamSport.getSportId()
            ));
        });
        return binding.getRoot();
    }

    public abstract int getButtonStringResourceId();

    public abstract int getTitleStringResourceId();

    public abstract void buttonClickedEvent(Team team);

}
