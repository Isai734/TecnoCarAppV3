package com.example.navi.tecnocarappv3.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.view.fragment.AutoEditFragment;

public class AutoEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_edit);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle=new Bundle();
        bundle.putInt("pos",getIntent().getIntExtra("pos",-1));
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        AutoEditFragment fragment = new AutoEditFragment();
        fragment.setArguments(bundle);
        transaction.replace(R.id.activity_auto_edit, fragment);
        transaction.commit();
    }
}
