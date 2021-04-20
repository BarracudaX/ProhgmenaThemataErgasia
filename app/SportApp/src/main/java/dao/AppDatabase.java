package dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import converters.GenderConverter;
import converters.LocalDateConverter;
import converters.SportTypeConverter;
import domain.Athlete;
import domain.Gender;
import domain.Sport;
import domain.SportType;
import domain.Team;

@Database(entities = {Team.class, Athlete.class, Sport.class}, version = 3)
@TypeConverters({LocalDateConverter.class, GenderConverter.class, SportTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract AthleteDao athleteDao();

    public abstract TeamDao teamDao();

    public abstract SportDao sportDao();

    public static AppDatabase getInstance(Context context) {
        return instance != null ? instance : buildDatabase(context);
    }

    private static AppDatabase buildDatabase(Context context){
        instance = Room.databaseBuilder(context, AppDatabase.class, "db")
                .allowMainThreadQueries()
                .build();
        SportDao sportDao = instance.sportDao();
        if(sportDao.loadAllSports().isEmpty()){
           sportDao.insert(new Sport("Basketball",Gender.BOTH,SportType.TEAM));
           sportDao.insert(new Sport("Football",Gender.MALE,SportType.TEAM));
           sportDao.insert(new Sport("Ping Pong",Gender.FEMALE,SportType.SINGLE));
        }
        return instance;
    }

}
