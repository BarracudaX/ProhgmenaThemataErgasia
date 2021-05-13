package dao;

import java.util.Set;

import domain.Match;
import domain.SingleMatch;
import domain.TeamMatch;

public interface MatchDao {

    void insertTeamMatch(TeamMatch match);

    void insertSingleMatch(SingleMatch match);

    void updateTeamMatch(TeamMatch match);

    void updateSingleMatch(SingleMatch match);

    void deleteTeamMatch(TeamMatch match);

    void deleteSingleMatch(SingleMatch match);

    Set<TeamMatch> selectTeamMatches();

    Set<SingleMatch> selectSingleMatches();

}
