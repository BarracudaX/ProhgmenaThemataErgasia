package domain;

import androidx.room.ColumnInfo;

public class AthleteIdNameModel {
    public AthleteIdNameModel() {
    }

    @ColumnInfo(name = "athleteCode")
    private long id;

    @ColumnInfo(name = "name")
    private String athleteName;

    @ColumnInfo(name = "surname")
    private String athleteSurname;


    public AthleteIdNameModel(long id, String athletetName) {
        this.id = id;
        this.athleteName = athleteName;
    }

    public long getId() {
        return id;
    }

    public String getAthleteFullName() {
        String fullname = athleteName + " " + athleteSurname;
        return fullname;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getAthleteSurname() {
        return athleteSurname;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public void setAthleteSurname(String athleteSurname) {
        this.athleteSurname = athleteSurname;
    }
}
