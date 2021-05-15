package viewmodels;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDate;

public class MainViewModel extends AndroidViewModel {

    private UpdateActiveLiveData<NavigationRequest> navDestinationRequest = new UpdateActiveLiveData<>();
    private UpdateActiveLiveData<Void> navigateBackRequest = new UpdateActiveLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
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


}
