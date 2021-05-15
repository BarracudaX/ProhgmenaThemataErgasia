package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dao.AppDatabase;
import dao.SportDao;
import domain.sport.AthleteSport;
import domain.sport.Sport;
import domain.sport.TeamSport;

public class SportViewModel extends AndroidViewModel {


    private final SportDao sportDao;
    private LiveData<List<TeamSport>> sportTeams;
    private LiveData<List<AthleteSport>> sportAthletes;

    public SportViewModel(@NonNull @NotNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        sportDao = appDatabase.sportDao();
    }

    public void insertAthleteSport(AthleteSport sport) {
        sportDao.insertAthleteSport(sport);
    }

    public void insertTeamSport(TeamSport sport){
        sportDao.insertTeamSport(sport);
    }

    public void updateAthleteSport(AthleteSport sport){
        sportDao.updateAthleteSport(sport);
    }

    public void updateSportTeam(TeamSport sport){
        sportDao.updateTeamSport(sport);
    }

    public LiveData<AthleteSport> findSportAthleteById(long sportId){
        return sportDao.findAthleteSpordById(sportId);
    }

    public LiveData<TeamSport> findSportTeamById(long sportId){
        return sportDao.findTeamSportById(sportId);
    }

    public LiveData<List<TeamSport>> teamSports(){
        if (sportTeams == null) {
            sportTeams = sportDao.teamSports();
        }

        return sportTeams;
    }

    public LiveData<List<AthleteSport>> athleteSports(){
        if(sportAthletes == null){
            sportAthletes = sportDao.athleteSports();
        }

        return sportAthletes;
    }

    public LiveData<List<Sport>> sports() {
        MediatorLiveData<List<Sport>> sports = new MediatorLiveData<>();
        sports.addSource(teamSports(), sportTeams -> {
            List<Sport> sportsList = new ArrayList<>();
            sportsList.addAll(sportTeams);
            sports.postValue(sportsList);
        });
        sports.addSource(athleteSports(), sportAthletes ->{
            List<Sport> sportsList = new ArrayList<>();
            sportsList.addAll(sportAthletes);
            sports.postValue(sportsList);
        });

        return sports;
     }

    public void deleteAthleteSport(AthleteSport sport) {
        sportDao.deleteAthleteSportById(sport.getSportId());
    }

    public void deleteTeamSport(TeamSport sport) {
        sportDao.deleteTeamSportById(sport.getSportId());
    }
}
