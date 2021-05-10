package domain;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public final class Sport {

    @PrimaryKey(autoGenerate = true)
    private long sportId;

    private String sportName;

    private Gender gender;

    private SportType sportType;

    public Sport(long sportId, String sportName, Gender gender, SportType sportType) {
        this.sportId = sportId;
        this.sportName = sportName;
        this.gender = gender;
        this.sportType = sportType;
    }

    @Ignore
    public Sport(String sportName, Gender gender, SportType sportType) {
        this.sportName = sportName;
        this.gender = gender;
        this.sportType = sportType;
    }

    public long getSportId() {
        return sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }
}
