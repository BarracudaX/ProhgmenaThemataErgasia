package dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface BaseDao<T> {

    @Insert
    void insert(T t);

    @Delete
    void delete(T t);

    @Update
    void update(T t);

}
