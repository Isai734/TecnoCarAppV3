package com.example.navi.tecnocarappv3.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.activities.AutoAddActivity;
import com.example.navi.tecnocarappv3.view.adapters.AutoAdapter;
import com.example.navi.tecnocarappv3.control.Autos;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutosFragment.OnLisAutoListener} interface
 * to handle interaction events.
 */
public class AutosFragment extends Fragment {

    //y esto solo lo copio y pego en otra clase y solo cambio los datos por los de la persona?
    //s soolo que tambien debes verificar que ya hallas creado tu xml para que coincida
    public OnLisAutoListener mListener;
    public Retrofit mRestAdapter;
    private RetrofitService retrofitService;
    private RecyclerView recyclerView;
    private AutoAdapter adapter;

    public AutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_autos, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabnuevoauto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), AutoAddActivity.class), 0);
            }
        });


        //Crear conexion al servicio REST
        mRestAdapter = new Retrofit.Builder().baseUrl(retrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //Crear conexion a la API
        retrofitService = mRestAdapter.create(RetrofitService.class);

        adapter = new AutoAdapter(null, mListener);
        recyclerView = (RecyclerView) v.findViewById(R.id.list_autos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        fillListAutos();
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK){
            fillListAutos();
        }
    }

    public void fillListAutos() {
        Call<Autos> loginCall = retrofitService.getAutos(SessionPreferences.get(getContext()).getClaveCliente());
        loginCall.enqueue(new Callback<Autos>() {
            @Override
            public void onResponse(Call<Autos> call, Response<Autos> response) {
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
                DataStore.getInstance().setAutoList(response.body().autos);
                adapter.swapData(response.body().autos);
                Snackbar.make(getActivity().findViewById(R.id.fabnuevoauto), "Recursos obtenidos", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Autos> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(R.id.fabnuevoauto), t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLisAutoListener) {
            mListener = (OnLisAutoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " Necesita implementar el evento de Listener del Iten del auto");
        }
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
    public interface OnLisAutoListener {
        // TODO: Update argument type and name
        void OnLisItemAutoListener(Auto auto);
        void OnAddAutoListener(int position);
        void OnDeleteAutoListener(Auto auto);
    }
}
