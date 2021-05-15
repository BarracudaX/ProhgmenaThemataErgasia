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
            throw new IllegalArgumentException("Maximum participants reached!");
        }

        if (athleteScores.contains(athleteScore)) {
            athleteScores.set(athleteScores.indexOf(athleteScore),athleteScore);//changes the score of the athlete.
        }else if(athleteScore.getSportId() == sportId){
            athleteScores.add(athleteScore);
        }else{//Athlete's sport is not the same as the sport of this match.
            throw new IllegalArgumentException("Athlete can't be added to the participants because they are not they haven't " +
                    "participated in the match's sport.");
        }

    }

}
