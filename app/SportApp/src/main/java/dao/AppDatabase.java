package dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.List;

import converters.AthleteSportTypeConverter;
import converters.GenderConverter;
import converters.LocalDateConverter;
import domain.Gender;
import domain.athlete.AthleteSingle;
import domain.athlete.AthleteTeam;
import domain.sport.AthleteSport;
import domain.sport.TeamSport;
import domain.team.Team;

@Database(entities = {AthleteSingle.class,AthleteTeam.class,AthleteSport.class,TeamSport.class,Team.class}, version = 9)
@TypeConverters({LocalDateConverter.class, GenderConverter.class, AthleteSportTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract AthleteDao athleteDao();

    public abstract TeamDao teamDao();

    public abstract SportDao sportDao();

    public static AppDatabase getInstance(Context context) {
        return instance != null ? instance : buildDatabase(context);
    }

    private static AppDatabase buildDatabase(Context context) {
        instance = Room.databaseBuilder(context, AppDatabase.class, "db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        SportDao sportdao = instance.sportDao();
        List<TeamSport> teamSports = sportdao.allTeamSports();
        if (teamSports.isEmpty()) {
            sportdao.insertTeamSport(new TeamSport("Basketball", Gender.MALE));
            sportdao.insertTeamSport(new TeamSport("Football", Gender.MALE));
            sportdao.insertTeamSport(new TeamSport("Football-F", Gender.FEMALE));
        }

        return instance;
    }

}
