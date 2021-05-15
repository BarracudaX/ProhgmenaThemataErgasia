package domain.match;

import java.util.Date;

import domain.sport.TeamSport;

public class TeamMatch extends Match {

    private TeamScore firstTeamScore;
    private TeamScore secondTeamScore;
    private long sportId;

    public TeamMatch(){

    }

    public TeamMatch(Date matchDate, String city, String country, TeamSport sport, TeamScore firstTeamScore, TeamScore secondTeamScore) {
        super(matchDate, city, country);
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.sportId = sport.getSportId();
    }

    public TeamScore getFirstTeamScore() {
        return firstTeamScore;
    }

    public TeamScore getSecondTeamScore() {
        return secondTeamScore;
    }

    public long getSportId() {
        return sportId;
    }
}
