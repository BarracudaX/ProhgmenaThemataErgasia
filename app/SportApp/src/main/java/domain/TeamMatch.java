package domain;

import java.time.LocalDateTime;

public class TeamMatch extends Match {

    private final Team firstTeam;
    private final int firstTeamScore;

    private final Team secondTeam;
    private final int secondTeamScore;


    protected TeamMatch(LocalDateTime matchDate, String city, String country,
                        Sport sport, Team firstTeam, int firstTeamScore, Team secondTeam, int secondTeamScore,String id) {
        super(matchDate, city, country, sport, id);
        this.firstTeam = firstTeam;
        this.firstTeamScore = firstTeamScore;
        this.secondTeam = secondTeam;
        this.secondTeamScore = secondTeamScore;
    }
}
