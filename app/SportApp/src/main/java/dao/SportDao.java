package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Athlete;
import domain.Sport;
import domain.SportIdNameModel;
import domain.SportType;

@Dao
public interface SportDao extends BaseDao<Sport> {

    @Query("SELECT * FROM Sport")
    LiveData<List<Sport>> liveLoadAllSports();

    @Query("SELECT * FROM Sport")
    List<Sport> loadAllSports();

    @Query("SELECT sportId,sportName FROM Sport")
    LiveData<List<SportIdNameModel>> sportIdsAndNames();

    @Query("SELECT sportId,sportName FROM Sport WHERE sportType=:type")
    LiveData<List<SportIdNameModel>> sportIdsAndNamesByType(SportType type);

    @Query("DELETE FROM Sport")
    void deleteAll();

    @Query("SELECT sportType FROM Sport WHERE sportId = :id")
    SportType sportTypeById(long id);

    @Query("SELECT * FROM Sport WHERE sportId = :sportId")
    Sport findSportById(long sportId);


}
