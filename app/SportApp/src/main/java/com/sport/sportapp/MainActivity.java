package com.sport.sportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.sport.sportapp.databinding.ActivityMainBinding;

import viewmodels.AthleteViewModel;
import viewmodels.MainViewModel;
import viewmodels.SportViewModel;
import viewmodels.TeamViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private SportViewModel sportViewModel;
    private TeamViewModel teamViewModel;
    private AthleteViewModel athleteViewModel;
    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        mainViewModel = viewModelProvider.get(MainViewModel.class);
        sportViewModel = viewModelProvider.get(SportViewModel.class);
        teamViewModel = viewModelProvider.get(TeamViewModel.class);
        athleteViewModel = viewModelProvider.get(AthleteViewModel.class);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration
                .Builder(navController.getGraph())
                .setOpenableLayout(binding.drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController,appBarConfiguration) ||  super.onSupportNavigateUp();
    }
}