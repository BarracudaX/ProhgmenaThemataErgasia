package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Team;

@Dao
public interface TeamDao extends BaseDao<Team>{

    @Query("SELECT * FROM Team")
    LiveData<List<Team>> loadAllTeams();

}
