package com.sport.sportapp.fragments.match;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentTeamMatchFormBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;
import com.sport.sportapp.spinner.TeamAdapter;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import domain.match.TeamMatch;
import domain.match.TeamScore;
import domain.sport.TeamSport;
import domain.team.Team;
import viewmodels.MatchViewModel;
import viewmodels.SportViewModel;
import viewmodels.TeamViewModel;

public abstract class TeamMatchFormFragment extends BaseFragment {

    protected TeamViewModel teamViewModel;
    protected SportViewModel sportViewModel;
    protected MatchViewModel matchViewModel;
    protected FragmentTeamMatchFormBinding binding;
    protected DatePicker datePicker;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
        sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);
        matchViewModel = new ViewModelProvider(getActivity()).get(MatchViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentTeamMatchFormBinding.inflate(inflater, container, false);
        binding.teamMatchFormTitle.setText(getTitleStringResourceId());
        binding.teamMatchFormButton.setText(getButtonStringResourceId());
        datePicker = new DatePicker(localDate -> binding.teamMatchDate.setText(localDate.toString()), LocalDate.now());
        binding.teamMatchDate.setOnClickListener(v -> datePicker.show(getChildFragmentManager(),"datePicker"));
        teamViewModel.teams().observe(getViewLifecycleOwner(),teams -> {
            binding.firstTeamInput.setAdapter(new TeamAdapter(getContext(),teams));
            binding.firstTeamInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Team selectedTeam = teams.get(position);
                    teamViewModel.teamsOfSport(selectedTeam.getSportId()).observe(getViewLifecycleOwner(),teamsOfSameSport ->{
                        teamsOfSameSport.remove(selectedTeam);
                        binding.secondTeamInput.setAdapter(new TeamAdapter(getContext(),teamsOfSameSport));
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
        binding.teamMatchFormButton.setOnClickListener(v -> {
            Team firstTeam = (Team) binding.firstTeamInput.getSelectedItem();
            Team secondTeam = (Team) binding.secondTeamInput.getSelectedItem();
            TeamSport sport = sportViewModel.getSportTeam(firstTeam.getSportId());
            TeamMatch teamMatch = new TeamMatch(
                    new Date(datePicker.getLocalDatePicked().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()),
                    binding.matchCityInput.getText().toString(),
                    binding.matchCountryInput.getText().toString(),
                    sport,
                    new TeamScore(firstTeam, Double.parseDouble(binding.firstTeamScoreInput.getText().toString())),
                    new TeamScore(secondTeam, Double.parseDouble(binding.secondTeamScoreInput.getText().toString()))
            );
            buttonClickedEvent(teamMatch);
        });

        return binding.getRoot();
    }

    public abstract int getButtonStringResourceId();

    public abstract int getTitleStringResourceId();

    public abstract void buttonClickedEvent(TeamMatch teamMatch);

}
