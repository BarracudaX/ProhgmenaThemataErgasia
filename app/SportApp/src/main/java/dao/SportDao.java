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

@Dao
public interface SportDao extends BaseDao<Sport> {

    @Query("SELECT * FROM Sport")
    List<Sport> loadAllSports();

    @Query("SELECT sportId,sportName FROM Sport")
    LiveData<List<SportIdNameModel>> sportIdsAndNames();

}
