package domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Athlete {

    @PrimaryKey(autoGenerate = true)
    public long athleteCode;

    public String name;

    public String surname;

    public String city;

    public String country;

    public int sportCode;

    public LocalDate dateOfBirth;

    public Athlete(String name, String surname, String city, String country, int sportCode, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.sportCode = sportCode;
        this.dateOfBirth = dateOfBirth;
    }

    public long getAthleteCode() {
        return athleteCode;
    }

    public String getName() {
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

    public int getSportCode() {
        return sportCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setAthleteCode(long athleteCode) {
        this.athleteCode = athleteCode;
    }
}
