package viewmodels;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import java.time.LocalDate;
import java.util.List;

import dao.AppDatabase;
import dao.AthleteDao;
import dao.SportDao;
import dao.TeamDao;
import domain.SportIdNameModel;
import domain.Team;

public class MainActivityViewModel extends ViewModel {

    private final TeamDao teamDao;
    private final SportDao sportDao;
    private final AthleteDao athleteDao;
    private final NavController navController;

    private LiveData<List<SportIdNameModel>> sportIdsAndNames ;
    private LiveData<List<Team>> teams;
    private MutableLiveData<LocalDate> pickedDate = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application, NavController navController) {
        this.navController = navController;
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

    public void updateTeam(Team team) {
        teamDao.update(team);
    }

    public LiveData<LocalDate> getPickedDate() {
        return pickedDate;
    }

    public void pickedDate(LocalDate pickedDate) {
        this.pickedDate.setValue(pickedDate);
    }

    public LiveData<Team> getTeamById(long teamId) {
        return teamDao.findById(teamId);
    }

    public void navigateTo(int destinationId, Bundle bundleRequest) {
        navController.navigate(destinationId,bundleRequest);
    }

    public void navigateBack() {
        navController.navigateUp();
    }

    public void deleteTeamById(long id) {
        teamDao.deleteById(id);
    }
}
