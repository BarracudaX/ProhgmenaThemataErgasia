package domain.sport;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import domain.Gender;

@Entity
public class AthleteSport extends Sport{

    @PrimaryKey(autoGenerate = true)
    private long sportId;

    private final AthleteSportType athleteSportType;

    public AthleteSport(long sportId, String sportName, Gender gender, AthleteSportType athleteSportType) {
        super(sportName, gender);
        this.athleteSportType = athleteSportType;
        this.sportId = sportId;
    }

    @Ignore
    public AthleteSport(String sportName, Gender gender, AthleteSportType athleteSportType) {
        super(sportName, gender);
        this.athleteSportType = athleteSportType;
    }

    @Override
    public long getSportId() {
        return sportId;
    }

    public AthleteSportType getAthleteSportType() {
        return athleteSportType;
    }
}
