package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dao.AppDatabase;
import dao.TeamDao;
import domain.team.Team;
import domain.team.TeamIdNameModel;

public class TeamViewModel extends AndroidViewModel {

    private final TeamDao teamDao;
    private LiveData<List<TeamIdNameModel>> teamIdsAndNames;
    private LiveData<List<Team>> teams;

    public TeamViewModel(@NonNull @NotNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        teamDao = appDatabase.teamDao();
    }

    public LiveData<List<TeamIdNameModel>> teamIdsAndNames() {
        if(teamIdsAndNames == null){
            teamIdsAndNames = teamDao.teamIdsAndNames();
        }
        return teamIdsAndNames;
    }

    public LiveData<List<Team>> teams() {

        if(teams == null){
            teams = teamDao.teams();
        }

        return  teams;
    }

    public void insertTeam(Team team) {
        teamDao.insert(team);
    }

    public void updateTeam(Team team) {
        teamDao.update(team);
    }

    public LiveData<Team> teamById(long teamId) {
        return teamDao.findById(teamId);
    }

    public void deleteTeamById(long id) {
        teamDao.deleteById(id);
    }

    public LiveData<List<Team>> teamsOfSport(long sportId) {
        return teamDao.teamsBySportId(sportId);
    }

}
