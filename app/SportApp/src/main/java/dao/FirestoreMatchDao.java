  package dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.AthleteScore;
import domain.Match;
import domain.SingleMatch;
import domain.TeamMatch;

public class FirestoreMatchDao implements MatchDao{

    private final static String MatchCollectionName = "matches";

    private final TeamDao teamDao;
    private final AthleteDao athleteDao;
    private final SportDao sportDao;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirestoreMatchDao(TeamDao teamDao, AthleteDao athleteDao, SportDao sportDao) {
        this.teamDao = teamDao;
        this.athleteDao = athleteDao;
        this.sportDao = sportDao;
    }

    @Override
    public void insertTeamMatch(TeamMatch match) {
        db.collection(MatchCollectionName).add(match)
                .addOnSuccessListener(documentReference ->{
                    match.setId(documentReference.getId());
                });
    }

    @Override
    public void insertSingleMatch(SingleMatch match) {
        db.collection(MatchCollectionName).add(match)
                .addOnSuccessListener( documentReference -> {
                   match.setId(documentReference.getId());
                });
    }

    @Override
    public void updateTeamMatch(TeamMatch match) {

    }

    @Override
    public void updateSingleMatch(SingleMatch match) {

    }

    @Override
    public void deleteMatch(Match match) {
        db.collection(MatchCollectionName).document(match.getId())
                .delete()
                .addOnSuccessListener( (Void unused) ->{
                    match.setId(null);
                });
    }

    @Override
    public LiveData<List<TeamMatch>> selectTeamMatches() {
        MutableLiveData<List<TeamMatch>> matches = new MutableLiveData<>();
        db.collection(MatchCollectionName).addSnapshotListener((querySnapshot, e) ->{
                    List<TeamMatch> result = new ArrayList<>();
                    for(DocumentSnapshot document : querySnapshot.getDocuments() ){
                        if(document.contains("firstTeamId")){
                            TeamMatch match = document.toObject(TeamMatch.class);
                            match.setId(document.getId());
                            match.setFirstTeam(teamDao.findTeamById(match.getFirstTeamId()));
                            match.setSecondTeam(teamDao.findTeamById(match.getSecondTeamId()));
                            match.setSport(sportDao.findSportById(match.getSportId()));
                            result.add(match);
                        }
                    }
                    matches.postValue(result);
                });
        return matches;
    }

    @Override
    public LiveData<List<SingleMatch>> selectSingleMatches() {
        MutableLiveData<List<SingleMatch>> matches = new MutableLiveData<>();
        db.collection(MatchCollectionName).addSnapshotListener((querySnapshot, e) -> {
            List<SingleMatch> result = new ArrayList<>();
            for(DocumentSnapshot document : querySnapshot.getDocuments() ){
                if(document.contains("athleteScores")){
                    SingleMatch match = document.toObject(SingleMatch.class);
                    match.setId(document.getId());
                    match.setSport(sportDao.findSportById(match.getSportId()));
                    for(AthleteScore athleteScore : match.getAthleteScores()){
                        athleteScore.setAthlete(athleteDao.findAthleteById(athleteScore.getAthleteId()));
                    }
                    result.add(match);
                }
            }
            matches.postValue(result);
        });
        return matches;
    }
    public void deleteById(long id){}

}
