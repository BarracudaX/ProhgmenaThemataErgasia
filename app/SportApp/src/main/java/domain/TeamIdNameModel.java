package domain;

import androidx.room.ColumnInfo;

public class TeamIdNameModel {

    @ColumnInfo(name = "teamId")
    private long id;

    @ColumnInfo(name = "teamName")
    private String teamName;


    public TeamIdNameModel(long id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

}
