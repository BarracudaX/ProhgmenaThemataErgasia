package domain.athlete;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.firestore.Exclude;

import java.util.Objects;

public class AthleteScore {

    private long sportId;
    private long athleteId;
    private double score;

    public AthleteScore(){

    }

    public AthleteScore(AthleteSingle athlete, double score) {
        this.sportId = athlete.getSportId();
        this.score = score;
        this.athleteId = athlete.getAthleteId();
    }

    @Exclude
    public long getSportId() {
        return sportId;
    }

    public double getScore() {
        return score;
    }

    public long getAthleteId(){
        return athleteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteScore that = (AthleteScore) o;
        return athleteId == that.athleteId;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(athleteId);
    }
}
