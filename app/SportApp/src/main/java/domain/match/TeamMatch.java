package domain.match;

import java.util.Date;

import domain.sport.TeamSport;

public class TeamMatch extends Match {

    private TeamScore firstTeam;
    private TeamScore secondTeam;
    private long sportId;

    public TeamMatch(){

    }

    public TeamMatch(Date matchDate, String city, String country, TeamSport sport, TeamScore firstTeam, TeamScore secondTeam) {
        super(matchDate, city, country);
        if (firstTeam.getSportId() != secondTeam.getSportId()) {
            throw new IllegalArgumentException("Can't create match for the two teams because they are not participating the same sport.");
        }
        if (sport.getSportId() != firstTeam.getSportId()) {
            throw new IllegalArgumentException("The sport given is not the sport in which both teams participated.");
        }
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public TeamScore getFirstTeam() {
        return firstTeam;
    }

    public TeamScore getSecondTeam() {
        return secondTeam;
    }

    public long getSportId() {
        return sportId;
    }
}
