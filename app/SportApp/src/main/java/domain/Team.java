package domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public final class Team {

    @PrimaryKey(autoGenerate = true)
    private long teamId;

    private String teamName;

    private String stadiumName;

    private String city;

    private String country;

    private LocalDate foundationDate;

    public Team(String teamName, String stadiumName, String city, String country, LocalDate foundationDate) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.city = city;
        this.country = country;
        this.foundationDate = foundationDate;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
