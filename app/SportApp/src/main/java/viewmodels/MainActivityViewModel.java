package viewmodels;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;

import java.time.LocalDate;
import java.util.List;

import dao.AppDatabase;
import dao.AthleteDao;
import dao.SportDao;
import dao.TeamDao;
import domain.Athlete;
import domain.Sport;
import domain.SportIdNameModel;
import domain.SportType;
import domain.Team;
import domain.TeamIdNameModel;

public class MainActivityViewModel extends AndroidViewModel {

    private final TeamDao teamDao;
    private final SportDao sportDao;
    private final AthleteDao athleteDao;
    private LiveData<List<SportIdNameModel>> sportIdsAndNames ;
    private LiveData<List<TeamIdNameModel>> teamIdsAndNames ;
    private LiveData<List<Team>> teams;
    private LiveData<List<Athlete>> athletes;
    private LiveData<List<Sport>> sports;
    private MutableLiveData<LocalDate> pickedDate = new MutableLiveData<>();
    private SingleLiveEvent<NavigationRequest> navDestinationRequest = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> navigateBackRequest = new SingleLiveEvent<>();

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
    public LiveData<List<TeamIdNameModel>> getTeamIdsAndNames(long sportId) {
        if (teamIdsAndNames == null) {
            teamIdsAndNames = teamDao.teamIdsAndNames(sportId);
        }
        return teamIdsAndNames;
    }
    public LiveData<List<Sport>> getSports(){
        if (teams == null) {
            sports = sportDao.liveLoadAllSports();
        }
        return sports;
    }

    public LiveData<List<Team>> getTeams() {
        if (teams == null) {
            teams = teamDao.loadAllTeams();
        }
        return teams;
    }
    public LiveData<List<Athlete>> getAthletes() {
        if (teams == null) {
            athletes = athleteDao.loadAllAthletes();
        }
        return athletes;
    }
    public LiveData<List<Athlete>> getAthletesBySport(long sportId) {
        if (teams == null) {
            athletes = athleteDao.loadAllAthletesBySport(sportId);
        }
        return athletes;
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

    public LiveData<NavigationRequest> navigationToEvent() {
        return navDestinationRequest;
    }

    public void navigateTo(int destinationId, Bundle bundleRequest) {
        navDestinationRequest.setValue(new NavigationRequest(destinationId,bundleRequest));
    }

    public LiveData<Void> navigateBackEvent() {
        return navigateBackRequest;
    }

    public void navigateBack() {
        navigateBackRequest.setValue(null);
    }

    public void deleteTeamById(long id) {
        teamDao.deleteById(id);
    }

    public void deleteAthleteById(long id) {
        athleteDao.deleteById(id);
    }

    public void insertAthlete(Athlete athlete) {athleteDao.insert(athlete);
    }

    public void deleteAll() {sportDao.deleteAll();
    }

    public SportType getSportType(long sportId){
        return sportDao.sportTypeById(sportId);
    }

    public void insertSport(Sport sport) {sportDao.insert(sport); }

    public LiveData<Integer> getSportCount(){
        return sportDao.getSportCount();
    }
}
