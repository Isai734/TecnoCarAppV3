package com.example.navi.tecnocarappv3.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.control.AutosInteractorImpl;
import com.example.navi.tecnocarappv3.control.Cita;
import com.example.navi.tecnocarappv3.control.CitasInteractorImpl;
import com.example.navi.tecnocarappv3.control.Orden;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.view.activities.AutoEditActivity;
import com.example.navi.tecnocarappv3.view.activities.LoginActivity;
import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.fragment.AcercaDe;
import com.example.navi.tecnocarappv3.view.fragment.AutosFragment;
import com.example.navi.tecnocarappv3.view.fragment.CitasListFragment;
import com.example.navi.tecnocarappv3.view.fragment.OrdenListFragment;
import com.example.navi.tecnocarappv3.view.fragment.Perfil;

public class Principal extends AppCompatActivity
        implements CitasListFragment.OnListCitasInteractorListener, NavigationView.OnNavigationItemSelectedListener, Perfil.OnFragmentInteractionListener, AutosFragment.OnLisAutoListener, OrdenListFragment.OnItemOrdenListner, PresenterViewListener {
    public final static String TAG = Principal.class.getSimpleName();
    Fragment fragment = null;
    private AutosInteractorImpl interactor;
    private CitasInteractorImpl interactorCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        interactor = new AutosInteractorImpl(this);
        interactorCita = new CitasInteractorImpl(this);
        setSupportActionBar(toolbar);

        Log.i(TAG, "Antes de pref");
        if (!SessionPreferences.get(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            Log.i(TAG, "Dentro de pref");
            finish();
        }
        Log.i(TAG, "Sesion : " + SessionPreferences.get(this).getClaveCliente());

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
        if (id == R.id.nav_perfil) {
            // Handle the camera action
            fragment = new Perfil();
            FragmentTransaction = true;
        } else if (id == R.id.nav_citas) {
            fragment = new CitasListFragment();
            FragmentTransaction = true;
        } else if (id == R.id.nav_autos) {
            fragment = new AutosFragment();
            FragmentTransaction = true;
        } else if (id == R.id.nav_servicios) {
            fragment = new OrdenListFragment();
            FragmentTransaction = true;
        } else if (id == R.id.nav_acerca) {
            fragment = new AcercaDe();
            FragmentTransaction = true;
        } else if (id == R.id.nav_logout) {
            SessionPreferences.get(this).logOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        if (FragmentTransaction) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void OnLisItemAutoListener(Auto auto) {

    }

    @Override
    public void OnAddAutoListener(int auto) {
        startActivityForResult(new Intent(this, AutoEditActivity.class).putExtra("pos", auto), 1);
    }

    @Override
    public void OnDeleteAutoListener(Auto auto) {
        lanzarAlertaAuto(auto);
    }

    public void lanzarAlertaAuto(final Auto auto) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Eliminar!");
        dialog.setMessage("Seguro que desea eliminar el registro de auto?");
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                interactor.delete(auto.getPlaca());
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void lanzarAlertaCita(final Cita cita) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Eliminar!");
        dialog.setMessage("Seguro que desea cancelar la cita?");
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                interactorCita.delete(cita.getId());
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void showProgress(boolean show) {
        if (show)
            MyDialogProgress.getInstance(this).show("Eliminando Registro");
        else
            MyDialogProgress.getInstance(this).dismiss();
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(findViewById(R.id.drawer_layout), response, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOperationSucess(ResponseApi response) {
        Snackbar.make(findViewById(R.id.content_principal), response.getMensaje(), Snackbar.LENGTH_LONG).show();
        ((AutosFragment) fragment).fillListAutos();
    }

    @Override
    public void OnItemOrdenListner(Orden item) {

    }

    @Override
    public void onListCitasListener(Cita item) {

    }

    @Override
    public void DelteListCitasListener(Cita item) {
        lanzarAlertaCita(item);
    }
}
