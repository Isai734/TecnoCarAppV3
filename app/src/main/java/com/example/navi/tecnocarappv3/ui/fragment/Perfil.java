package com.example.navi.tecnocarappv3.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.control.Autos;
import com.example.navi.tecnocarappv3.control.Personas;
import com.example.navi.tecnocarappv3.datos.ResponseApi;
import com.example.navi.tecnocarappv3.datos.RetrofitService;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Perfil.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Perfil extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Retrofit mRestAdapter;
    private RetrofitService retrofitService;
    List<Personas.Persona> persona;
    Personas.Persona mself;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Aqui manda a traer el servicio web y consultalo y solo has referencia a los campos que ya creas con el metodo find
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabeditarperfil);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                EditarPerfil fragment = new EditarPerfil();
                transaction.replace(R.id.content_principal, fragment);
                transaction.commit();

            }
        });


        //Crear conexion al servicio REST
        mRestAdapter = new Retrofit.Builder().baseUrl(retrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //Crear conexion a la API
        retrofitService = mRestAdapter.create(RetrofitService.class);

        //mandamos consutar el servici web
        getPerfil();//Aqui mismo ya se llena nuestro arreglo de personas el cual estara contenido en la posicion [0]
        mself=persona.get(0);//Aqui lo seteamos --Ahora solo falta setear los campos

        TextView nombre =(TextView)v.findViewById(R.id.txv_nombre_persona);
        TextView paterno = (TextView)v.findViewById(R.id.paterno_persona);
        TextView materno = (TextView)v.findViewById(R.id.materno_persona);
        TextView telefono = (TextView) v.findViewById(R.id.telefono_persona);
        TextView direccion  = (TextView) v.findViewById(R.id.direccion_persona);
        TextView email = (TextView) v.findViewById(R.id.email_persona);
        TextView rfc = (TextView)v.findViewById(R.id.rfc_persona);

        nombre.setText(mself.nombre);
        paterno.setText(mself.apellido_paterno);
        materno.setText(mself.apellido_materno);
        telefono.setText(mself.telefono);
        direccion.setText(mself.direccion);
        email.setText(mself.email);
        rfc.setText(mself.rfc);

        return v;
    }

    public void getPerfil() {
        Call<Personas> loginCall = retrofitService.getPersona(SessionPreferences.get(getContext()).getClaveCliente());
        loginCall.enqueue(new Callback<Personas>() {
            @Override
            public void onResponse(Call<Personas> call, Response<Personas> response) {
                // Mostrar progreso

                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        ResponseApi responseApi = ResponseApi.fromResponseBody(response.errorBody());

                        error = responseApi.getMensaje();
                        Log.d("LoginActivity", responseApi.getMensaje());
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.d("LoginActivity", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }

                persona=(response.body().persona);
                Snackbar.make(getActivity().findViewById(R.id.fabnuevoauto), "Recursos obtenidos", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Personas> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(R.id.fabnuevoauto), t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
