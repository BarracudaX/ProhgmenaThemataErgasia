package domain;

import androidx.room.ColumnInfo;

public class SportIdNameModel {

    @ColumnInfo(name = "sportId")
    private long id;

    @ColumnInfo(name = "sportName")
    private String sportName;


    public SportIdNameModel(long id, String sportName) {
        this.id = id;
        this.sportName = sportName;
    }

    public long getId() {
        return id;
    }

    public String getSportName() {
        return sportName;
    }

}
