package domain;

import java.time.LocalDateTime;

public class TeamMatch extends Match {

    private final Team firstTeam;
    private final int firstTeamScore;

    private final Team secondTeam;
    private final int secondTeamScore;


    protected TeamMatch(boolean isTeamMatch, LocalDateTime matchDate, String city, String country, Sport sport, Team firstTeam, int firstTeamScore, Team secondTeam, int secondTeamScore) {
        super(isTeamMatch, matchDate, city, country, sport);
        this.firstTeam = firstTeam;
        this.firstTeamScore = firstTeamScore;
        this.secondTeam = secondTeam;
        this.secondTeamScore = secondTeamScore;
    }
}
