package com.example.navi.tecnocarappv3.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.navi.tecnocarappv3.view.activities.LoginActivity;
import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.control.Autos;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.fragment.AcercaDe;
import com.example.navi.tecnocarappv3.view.fragment.AutosFragment;
import com.example.navi.tecnocarappv3.view.fragment.Citas;
import com.example.navi.tecnocarappv3.view.fragment.Perfil;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Perfil.OnFragmentInteractionListener,AutosFragment.OnLisAutoListener {
    public final static String TAG=Principal.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Log.i(TAG,"Antes de pref");
        if(!SessionPreferences.get(this).isLoggedIn()){
            startActivity(new Intent(this,LoginActivity.class));
            Log.i(TAG,"Dentro de pref");
            finish();
        }
        Log.i(TAG,"Sesion : "+SessionPreferences.get(this).getClaveCliente());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Boolean FragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.nav_perfil) {
            // Handle the camera action
            fragment = new Perfil();
            FragmentTransaction = true;
        } else if (id == R.id.nav_citas) {
            fragment = new Citas();
            FragmentTransaction = true;
        } else if (id == R.id.nav_autos) {
            fragment = new AutosFragment();
            FragmentTransaction = true;
        } else if (id == R.id.nav_servicios) {

        } else if (id == R.id.nav_acerca) {
            fragment = new AcercaDe();
            FragmentTransaction = true;
        } else if (id == R.id.nav_logout) {
            SessionPreferences.get(this).logOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();

        }

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_principal, fragment).commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void OnLisItemAutoListener(Autos.Auto auto) {

    }
}
