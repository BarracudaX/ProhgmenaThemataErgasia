package domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class Team {

    @PrimaryKey
    private long teamId;
    private String teamName;
    private String stadiumName;
    private String city;
    private String country;
    private LocalDate foundationYear;
    private Sport sport;

    protected Team(){

    }

    public Team(Sport sport, String teamName, String stadiumName, String city, String country, LocalDate foundationYear) {
        this.sport = sport;
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.city = city;
        this.country = country;
        this.foundationYear = foundationYear;
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

    public LocalDate getFoundationYear() {
        return foundationYear;
    }

}
