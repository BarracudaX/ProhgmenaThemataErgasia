package domain.athlete;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

import domain.sport.AthleteSport;

@Entity(foreignKeys = {
        @ForeignKey(entity = AthleteSport.class,parentColumns = "sportId",childColumns = "sportId",onDelete = ForeignKey.CASCADE)
})
public class AthleteSingle extends Athlete{

    @PrimaryKey(autoGenerate = true)
    private Long athleteId;

    private final long sportId;

    @Ignore
    public AthleteSingle(String firstName, String lastName, String city, String country, LocalDate dateOfBirth, long sportId) {
        super(firstName, lastName, city, country, dateOfBirth);
        this.sportId = sportId;
    }

    public AthleteSingle(String firstName, String lastName,
                         String city, String country, LocalDate dateOfBirth, long athleteId, long sportId) {
        super(firstName, lastName, city, country, dateOfBirth);
        this.athleteId = athleteId;
        this.sportId = sportId;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public long getSportId() {
        return sportId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }
}
