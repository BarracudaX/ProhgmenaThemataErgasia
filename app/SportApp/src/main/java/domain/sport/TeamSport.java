package domain.sport;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import domain.Gender;

@Entity
public class TeamSport extends Sport {

    @PrimaryKey(autoGenerate = true)
    private long sportId;

    public TeamSport(long sportId, String sportName, Gender gender) {
        super(sportName, gender);
        this.sportId = sportId;
    }

    @Ignore
    public TeamSport(String sportName, Gender gender) {
        super(sportName, gender);
    }

    @Override
    public long getSportId() {
        return sportId;
    }
}
