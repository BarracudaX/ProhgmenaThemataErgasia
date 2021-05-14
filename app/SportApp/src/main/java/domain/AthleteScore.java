package domain;

import com.google.firebase.firestore.Exclude;

public class AthleteScore {

    private Athlete athlete;
    private long athleteId;
    private double score;

    public AthleteScore(){

    }

    public AthleteScore(Athlete athlete, double score) {
        this.athlete = athlete;
        this.score = score;
        this.athleteId = athlete.getAthleteCode();
    }

    @Exclude
    public Athlete getAthlete() {
        return athlete;
    }

    public double getScore() {
        return score;
    }

    public long getAthleteId(){
        return athleteId;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }


}
