package viewmodels;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDate;
import java.util.List;

import dao.AppDatabase;
import dao.AthleteDao;
import dao.FirestoreMatchDao;
import dao.MatchDao;
import dao.SportDao;
import dao.TeamDao;
import domain.Athlete;
import domain.AthleteIdNameModel;
import domain.SingleMatch;
import domain.Sport;
import domain.SportIdNameModel;
import domain.SportType;
import domain.Team;
import domain.TeamIdNameModel;
import domain.TeamMatch;

public class MainActivityViewModel extends AndroidViewModel {

    private final TeamDao teamDao;
    private final SportDao sportDao;
    private final AthleteDao athleteDao;
    private final FirestoreMatchDao matchDao;
    private LiveData<List<SportIdNameModel>> sportIdsAndNames ;
    private LiveData<List<AthleteIdNameModel>> athleteIdAndName ;
    private LiveData<List<TeamIdNameModel>> teamIdsAndNames ;
    private LiveData<List<Team>> teams;
    private LiveData<List<Athlete>> athletes;
    private List<Athlete> athletesBySportId;
    private LiveData<List<Sport>> sports;
    private MutableLiveData<LocalDate> pickedDate = new MutableLiveData<>();
    private UpdateActiveLiveData<NavigationRequest> navDestinationRequest = new UpdateActiveLiveData<>();
    private UpdateActiveLiveData<Void> navigateBackRequest = new UpdateActiveLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        teamDao = appDatabase.teamDao();
        sportDao = appDatabase.sportDao();
        athleteDao = appDatabase.athleteDao();
        matchDao = new FirestoreMatchDao(teamDao,athleteDao, sportDao);
    }

    public LiveData<List<SportIdNameModel>> getSportIdsAndNames() {
        if (sportIdsAndNames == null) {
            sportIdsAndNames = sportDao.sportIdsAndNames();
        }
        return sportIdsAndNames;
    }
    public LiveData<List<SportIdNameModel>> getSportIdsAndNamesByType(SportType sportType) {
        if (sportIdsAndNames == null) {
            sportIdsAndNames = sportDao.sportIdsAndNamesByType(sportType);
        }
        return sportIdsAndNames;
    }
    public LiveData<List<AthleteIdNameModel>> getAthleteIdAndName() {
        if (athleteIdAndName == null) {
            athleteIdAndName = athleteDao.athleteIdAndName();
        }
        return athleteIdAndName;
    }
    public LiveData<List<TeamIdNameModel>> getTeamIdsAndNamesBySport(long sportId) {
        if (teamIdsAndNames == null) {
            teamIdsAndNames = teamDao.teamIdsAndNamesBySport(sportId);
        }
        return teamIdsAndNames;
    }
    public LiveData<List<TeamIdNameModel>> getTeamIdsAndNames() {
        if (teamIdsAndNames == null) {
            teamIdsAndNames = teamDao.teamIdsAndNames();
        }
        return teamIdsAndNames;
    }
    public LiveData<List<Sport>> getSports(){
        if (sports == null) {
            sports = sportDao.liveLoadAllSports();
        }
        return sports;
    }
    public Sport getSportById(long id){
        return sportDao.findSportById(id);
    }

    public LiveData<List<Team>> getTeams() {
        if (teams == null) {
            teams = teamDao.liveLoadAllTeams();
        }
        return teams;
    }
    public LiveData<List<Athlete>> getAthletes() {
        if (athletes == null) {
            athletes = athleteDao.loadAllAthletes();
        }
        return athletes;
    }
    public List<Athlete> getAthletesBySportId(long sportId) {
        if (teams == null) {
            athletesBySportId = athleteDao.findAthletesBySport(sportId);
        }
        return athletesBySportId;
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
    public void deleteMatchById(long id) {
        matchDao.deleteById(id);
    }

    public void insertAthlete(Athlete athlete) {athleteDao.insert(athlete);
    }

    public void deleteAll() {sportDao.deleteAll();
    }

    public SportType getSportType(long sportId){
        return sportDao.sportTypeById(sportId);
    }

    public void insertSport(Sport sport) {sportDao.insert(sport); }

    public void updateAthlete(Athlete athlete) {
        athleteDao.update(athlete);
    }

    public LiveData<Athlete> getAthleteById(long athleteId){
        return athleteDao.findById(athleteId);
    }
    public Athlete findAthleteById(long athleteId){
        return athleteDao.findAthleteById(athleteId);
    }

    public LiveData<List<Team>> getTeamsOfSport(long sportId) {
        return teamDao.teamsBySportId(sportId);
    }
    public LiveData<List<SingleMatch>> getSingleMatches(){
        return(matchDao.selectSingleMatches());
    }
    public LiveData<List<TeamMatch>> getTeamMatches(){
        return(matchDao.selectTeamMatches());
    }

    public void insertSingleMatch(SingleMatch singleMatch) {
        matchDao.insertSingleMatch(singleMatch);
    }

    public void insertTeamMatch(TeamMatch teamMatch) {
        matchDao.insertTeamMatch(teamMatch);
    }
}
