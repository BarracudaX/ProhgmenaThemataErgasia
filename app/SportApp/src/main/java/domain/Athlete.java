package domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
@Entity(foreignKeys = {
        @ForeignKey(entity = Sport.class,parentColumns = "sportId",childColumns = "sportId",onDelete = ForeignKey.CASCADE),
})
public class Athlete {

    @PrimaryKey(autoGenerate = true)
    public long athleteCode;

    public String name;

    public String surname;

    public String city;

    public String country;

    private long sportId;

    public LocalDate dateOfBirth;


    @Ignore
    public Athlete(String name, String surname, String city, String country, LocalDate dateOfBirth, long sportId) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.sportId = sportId;
    }

    public Athlete(long athleteCode, String name, String surname, String city, String country, LocalDate dateOfBirth, long sportId) {
        this.athleteCode = athleteCode;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.sportId = sportId;
    }

    public long getAthleteCode() {
        return athleteCode;
    }

    public String getAthleteName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public long getSportId() { return sportId; }

    public void setAthleteCode(long athleteCode) {
        this.athleteCode = athleteCode;
    }

}
