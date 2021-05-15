package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dao.AppDatabase;
import dao.AthleteDao;
import domain.athlete.AthleteSingle;
import domain.athlete.AthleteTeam;

public class AthleteViewModel extends AndroidViewModel {

    private final AthleteDao athleteDao;

    public AthleteViewModel(@NonNull @NotNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        athleteDao = appDatabase.athleteDao();
    }

    public void insertAthleteSingle(AthleteSingle athlete){
        athleteDao.insertAthleteSingle(athlete);
    }

    public void insertAthleteTeam(AthleteTeam athlete){
        athleteDao.insertAthleteTeam(athlete);
    }

    public void updateAthleteSingle(AthleteSingle athlete){
        athleteDao.updateAthleteSingle(athlete);
    }

    public void updateAthleteTeam(AthleteTeam athlete){
        athleteDao.updateAthleteTeam(athlete);
    }

    public void deleteAthleteSingle(AthleteSingle athlete){
        athleteDao.deleteAthleteSingleById(athlete.getAthleteId());
    }

    public void deleteAthleteTeam(AthleteTeam athlete){
        athleteDao.deleteAthleteTeamById(athlete.getAthleteId());
    }

    public LiveData<AthleteSingle> findAthleteSingleById(long athleteId){
        return athleteDao.findAthleteSingleById(athleteId);
    }

    public LiveData<AthleteTeam> findAthleteTeamById(long athleteId){
        return athleteDao.findAthleteTeamById(athleteId);
    }


    public LiveData<List<AthleteSingle>> athletesSingle() {
        return athleteDao.athleteSingles();
    }

    public LiveData<List<AthleteTeam>> athletesTeam(){
        return athleteDao.athleteTeams();
    }
}
