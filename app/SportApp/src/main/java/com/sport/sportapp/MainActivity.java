package com.sport.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.ActivityMainBinding;
import com.sport.sportapp.fragments.InsertTeamFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, InsertTeamFragment.class,null)
                    .setReorderingAllowed(true)
                    .addToBackStack("")
                    .commit();
        }
    }
}