package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDate;
import java.util.List;

import dao.AppDatabase;
import dao.AthleteDao;
import dao.SportDao;
import dao.TeamDao;
import domain.Sport;
import domain.SportIdNameModel;
import domain.Team;

public class MainActivityViewModel extends AndroidViewModel {

    private final TeamDao teamDao;
    private final SportDao sportDao;
    private final AthleteDao athleteDao;
    private LiveData<List<SportIdNameModel>> sportIdsAndNames ;
    private LiveData<List<Team>> teams;
    private MutableLiveData<LocalDate> pickedDate = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        teamDao = appDatabase.teamDao();
        sportDao = appDatabase.sportDao();
        athleteDao = appDatabase.athleteDao();
    }

    public LiveData<List<SportIdNameModel>> getSportIdsAndNames() {
        if (sportIdsAndNames == null) {
            sportIdsAndNames = sportDao.sportIdsAndNames();
        }
        return sportIdsAndNames;
    }

    public LiveData<List<Team>> getTeams() {
        if (teams == null) {
            teams = teamDao.loadAllTeams();
        }
        return teams;
    }

    public void insertTeam(Team team) {
        teamDao.insert(team);
    }

    public LiveData<LocalDate> getPickedDate() {
        return pickedDate;
    }

    public void pickedDate(LocalDate pickedDate) {
        this.pickedDate.setValue(pickedDate);
    }
}
