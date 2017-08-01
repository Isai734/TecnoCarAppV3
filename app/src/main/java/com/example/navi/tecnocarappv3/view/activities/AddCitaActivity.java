package com.example.navi.tecnocarappv3.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.view.fragment.NuevaCita;

public class AddCitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cita);

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        NuevaCita fragment = new NuevaCita();
        transaction.replace(R.id.activity_add_cita, fragment);
        transaction.commit();
    }
}
