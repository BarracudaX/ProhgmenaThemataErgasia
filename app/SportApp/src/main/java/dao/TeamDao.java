package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.team.Team;
import domain.team.TeamIdNameModel;

@Dao
public interface TeamDao {

    @Insert
    void insert(Team team);

    @Update
    void update(Team team);

    @Delete
    void delete(Team team);

    @Query("DELETE FROM Team WHERE teamId = :teamId")
    void deleteById(long teamId);

    @Query("SELECT * FROM Team WHERE sportId = :sportId")
    LiveData<List<Team>> teamsBySportId(long sportId);

    @Query("SELECT * FROM Team")
    LiveData<List<Team>> teams();

    @Query("SELECT teamId,teamName FROM Team")
    LiveData<List<TeamIdNameModel>> teamIdsAndNames();

    @Query("SELECT * FROM Team WHERE teamId = :teamId")
    LiveData<Team> findById(long teamId);


    @Query("SELECT * FROM Team WHERE teamId = :teamId")
    Team getById(long teamId);
}
