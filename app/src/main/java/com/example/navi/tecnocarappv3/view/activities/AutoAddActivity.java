package com.example.navi.tecnocarappv3.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.navi.tecnocarappv3.view.fragment.NuevoAuto;

public class AutoAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.navi.tecnocarappv3.R.layout.activity_auto_add);

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        NuevoAuto fragment = new NuevoAuto();
        transaction.replace(com.example.navi.tecnocarappv3.R.id.activity_auto_add, fragment);
        transaction.commit();
    }
}
