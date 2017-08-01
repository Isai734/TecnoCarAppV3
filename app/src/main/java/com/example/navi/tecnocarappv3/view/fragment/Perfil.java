package com.example.navi.tecnocarappv3.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navi.tecnocarappv3.control.Persona;
import com.example.navi.tecnocarappv3.control.PersonaInteractorImpl;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.MyDialogProgress;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;
import com.example.navi.tecnocarappv3.view.activities.EditPerfilActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Perfil.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Perfil extends Fragment implements PresenterViewListener {

    private static final String TAG = Perfil.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    Persona mself;
    private TextView nombre;
    private TextView paterno;
    private TextView materno;
    private TextView telefono;
    private TextView direccion;
    private TextView email;
    private TextView rfc;
    PersonaInteractorImpl interactor;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Aqui manda a traer el servicio web y consultalo y solo has referencia a los campos que ya creas con el metodo find
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(com.example.navi.tecnocarappv3.R.layout.fragment_perfil, container, false);
        interactor = new PersonaInteractorImpl(this);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(com.example.navi.tecnocarappv3.R.id.fabeditarperfil);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivityForResult(new Intent(getContext(),EditPerfilActivity.class),0);
            }
        });

        nombre = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.txv_nombre_persona);
        paterno = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.paterno_persona);
        materno = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.materno_persona);
        telefono = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.telefono_persona);
        direccion = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.direccion_persona);
        email = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.email_persona);
        rfc = (TextView) v.findViewById(com.example.navi.tecnocarappv3.R.id.rfc_persona);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            interactor.get(SessionPreferences.get(getContext()).getClaveCliente());
            mself= DataStore.getInstance().getPersona();
            setData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "Clave : " + SessionPreferences.get(getContext()).getClaveCliente());
        interactor.get(SessionPreferences.get(getContext()).getClaveCliente());
    }

    // TODO: Rename method, put argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress(boolean show) {
        if (show)
            MyDialogProgress.getInstance(getContext()).show("Obteniedo perfil");
        else
            MyDialogProgress.getInstance(getContext()).dismiss();
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(getView().findViewById(com.example.navi.tecnocarappv3.R.id.fabeditarperfil), response, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOperationSucess(ResponseApi response) {
        Snackbar.make(getView().findViewById(com.example.navi.tecnocarappv3.R.id.fabeditarperfil), response.getMensaje(), Snackbar.LENGTH_LONG).show();
        mself= DataStore.getInstance().getPersona();
        setData();
    }

    public void setData() {
        nombre.setText(mself.nombre);
        paterno.setText(mself.apellido_paterno);
        materno.setText(mself.apellido_materno);
        telefono.setText(mself.telefono);
        direccion.setText(mself.direccion);
        email.setText(mself.email);
        rfc.setText(mself.rfc);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
