package dao;

import androidx.lifecycle.LiveData;

import java.util.List;

import domain.match.Match;
import domain.match.AthletesMatch;
import domain.match.TeamMatch;

public interface MatchDao {

    void insertTeamMatch(TeamMatch match);

    void insertAthleteMatch(AthletesMatch match);

    void updateTeamMatch(TeamMatch match);

    void updateAthleteMatch(AthletesMatch match);

    void deleteTeamMatch(TeamMatch match);

    LiveData<List<TeamMatch>> teamMatches();

    LiveData<List<AthletesMatch>> athleteMatches();

    LiveData<TeamMatch> findById(String teamMatchId);

    void deleteById(long id);

}
