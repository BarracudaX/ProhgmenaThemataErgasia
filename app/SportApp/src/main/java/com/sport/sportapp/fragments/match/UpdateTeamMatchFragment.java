package com.sport.sportapp.fragments.match;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentTeamMatchFormBinding;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.ZoneId;

import domain.match.TeamMatch;
import domain.team.Team;

public class UpdateTeamMatchFragment extends TeamMatchFormFragment {

    private static String ID_KEY = "MATCH_ID";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        matchViewModel.findTeamMatchById(getArguments().getString(ID_KEY)).observe(getViewLifecycleOwner(), teamMatch -> {
            binding.firstTeamScoreInput.setText(String.valueOf(teamMatch.getFirstTeamScore().getScore()));
            binding.secondTeamScoreInput.setText(String.valueOf(teamMatch.getSecondTeamScore().getScore()));
            binding.matchCityInput.setText(teamMatch.getCity());
            binding.matchCountryInput.setText(teamMatch.getCity());
            LocalDate matchDate = teamMatch.getMatchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            datePicker.setLocalDatePicked(matchDate);
            binding.teamMatchDate.setText(matchDate.toString());
            for (int i = 0; i < binding.firstTeamInput.getAdapter().getCount(); i++) {
                Team firstTeam = (Team) binding.firstTeamInput.getItemAtPosition(i);
                if (firstTeam.getTeamId() == teamMatch.getFirstTeamScore().getTeamId()) {
                    binding.firstTeamInput.setSelection(i,true);
                    break;
                }
            }
            for (int i = 0; i < binding.secondTeamInput.getAdapter().getCount(); i++) {
                Team firstTeam = (Team) binding.secondTeamInput.getItemAtPosition(i);
                if (firstTeam.getTeamId() == teamMatch.getFirstTeamScore().getTeamId()) {
                    binding.secondTeamInput.setSelection(i);
                    break;
                }
            }
        });
    }

    @Override
    public int getButtonStringResourceId() {
        return R.string.update_team_match_title;
    }

    @Override
    public int getTitleStringResourceId() {
        return R.string.update_team_match_title;
    }

    @Override
    public void buttonClickedEvent(TeamMatch teamMatch) {
        teamMatch.setId(getArguments().getString(ID_KEY));
        matchViewModel.updateTeamMatch(teamMatch);
        mainViewModel.navigateBack();
        Toast.makeText(getContext(), "Team Match updated successfully", Toast.LENGTH_LONG).show();
    }

    public static Bundle getBundleRequest(String teamMatchId){
        Bundle bundle = new Bundle();
        bundle.putString(ID_KEY, teamMatchId);
        return bundle;
    }
}
