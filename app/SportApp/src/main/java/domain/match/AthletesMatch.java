package domain.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import domain.sport.AthleteSport;
import domain.athlete.AthleteScore;

public class AthletesMatch extends Match {

    private final List<AthleteScore> athleteScores = new ArrayList<>();

    private long sportId;

    private int maximumParticipants;

    public AthletesMatch(){

    }

    public AthletesMatch(Date matchDate, String city, String country, AthleteSport athleteSport) {
        super(matchDate, city, country);
        sportId = athleteSport.getSportId();
        maximumParticipants = athleteSport.getAthleteSportType().getNumberOfAthletes();
    }

    public List<AthleteScore> getAthleteScores() {
        return Collections.unmodifiableList(athleteScores);
    }

    public long getSportId() {
        return sportId;
    }

    public void addAthlete(AthleteScore athleteScore){
        if (athleteScores.size() == maximumParticipants) {
            throw new IllegalArgumentException("Can't fit more participants");
        }
        athleteScores.add(athleteScore);
    }

}
