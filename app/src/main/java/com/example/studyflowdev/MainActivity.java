package com.example.studyflowdev;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.studyflowdev.ui.DashboardFragment;
import com.example.studyflowdev.ui.MetasFragment;
import com.example.studyflowdev.ui.PerfilFragment;
import com.example.studyflowdev.ui.TecnologiasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Define o fragment inicial
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (id == R.id.nav_tecnologias) {
                selectedFragment = new TecnologiasFragment();
            } else if (id == R.id.nav_metas) {
                selectedFragment = new MetasFragment();
            } else if (id == R.id.nav_perfil) {
                selectedFragment = new PerfilFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
