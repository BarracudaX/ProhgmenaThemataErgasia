package domain;

import com.google.firebase.firestore.Exclude;

public class AthleteScore {

    private final Athlete athlete;
    private final double score;

    public AthleteScore(Athlete athlete, double score) {
        this.athlete = athlete;
        this.score = score;
    }

    @Exclude
    public Athlete getAthlete() {
        return athlete;
    }

    public double getScore() {
        return score;
    }

    public long getAthleteId(){
        return athlete.getAthleteCode();
    }
}
