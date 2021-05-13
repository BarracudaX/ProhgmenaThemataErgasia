package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Sport;
import domain.SportIdNameModel;
import domain.Team;
import domain.TeamIdNameModel;

@Dao
public interface TeamDao extends BaseDao<Team>{

    @Query("SELECT * FROM Team")
    LiveData<List<Team>> liveLoadAllTeams();

    @Query("SELECT * FROM Team WHERE teamId = :teamId")
    LiveData<Team> findById(long teamId);

    @Query("SELECT * FROM Team WHERE teamId = :teamId")
    Team findTeamById(long teamId);

    @Query("DELETE FROM Team WHERE teamId = :teamId")
    void deleteById(long teamId);

    @Query("SELECT * FROM Team WHERE sportId = :sportId")
    LiveData<List<Team>> teamsBySportId(long sportId);

    @Query("SELECT teamId,teamName FROM Team WHERE sportId = :id")
    LiveData<List<TeamIdNameModel>> teamIdsAndNamesBySport(long id);
    
    @Query("SELECT teamId,teamName FROM Team")
    LiveData<List<TeamIdNameModel>> teamIdsAndNames();

    @Query("SELECT * FROM Team")
    List<Team> loadAllTeams();
}
