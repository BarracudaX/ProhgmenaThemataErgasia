  package dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import domain.athlete.AthleteScore;
import domain.match.Match;
import domain.match.AthletesMatch;
import domain.match.TeamMatch;

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
    public void insertSingleMatch(AthletesMatch match) {
        db.collection(MatchCollectionName).add(match)
                .addOnSuccessListener( documentReference -> {
                   match.setId(documentReference.getId());
                });
    }

    @Override
    public void updateTeamMatch(TeamMatch match) {

    }

    @Override
    public void updateSingleMatch(AthletesMatch match) {

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
                            result.add(match);
                        }
                    }
                    matches.postValue(result);
                });
        return matches;
    }

    @Override
    public LiveData<List<AthletesMatch>> selectSingleMatches() {
        MutableLiveData<List<AthletesMatch>> matches = new MutableLiveData<>();
          db.collection(MatchCollectionName).addSnapshotListener((querySnapshot, e) -> {
            List<AthletesMatch> result = new ArrayList<>();
            for(DocumentSnapshot document : querySnapshot.getDocuments() ){
                if(document.contains("athleteScores")){
                    AthletesMatch match = document.toObject(AthletesMatch.class);
                    match.setId(document.getId());
                    result.add(match);
                }
            }
            matches.postValue(result);
        });
        return matches;
    }
}
