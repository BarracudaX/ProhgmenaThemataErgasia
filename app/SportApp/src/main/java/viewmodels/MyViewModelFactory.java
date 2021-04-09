package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

public class MyViewModelFactory implements ViewModelProvider.Factory {

    private final NavController navController;
    private final Application application;

    public MyViewModelFactory(Application application,NavController navController) {
        this.navController = navController;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        //MainActivityViewModel extends ViewModel
        @SuppressWarnings("unchecked")
        T t = (T) new MainActivityViewModel(application, navController);

        return t;
    }
}
