package domain;

import androidx.room.ColumnInfo;

public class AthleteIdNameSportModel {

    @ColumnInfo(name = "athleteCode")
    private long id;

    @ColumnInfo(name = "athleteName")
    private String athleteName;

    @ColumnInfo(name = "sportId")
    private long sportId;

    public AthleteIdNameSportModel(long id, String athleteName, long sportId) {
        this.id = id;
        this.athleteName = athleteName;
        this.sportId = sportId;
    }

    public long getAthleteCode() {
        return id;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public long getSportId(){return sportId;}

}
