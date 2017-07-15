package com.example.navi.tecnocarappv3.control;

import android.util.Log;

import com.example.navi.tecnocarappv3.control.OperInteractor;
import com.example.navi.tecnocarappv3.control.Personas;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;
import com.example.navi.tecnocarappv3.view.activities.LoginActivity;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Isai on 15/07/2017.
 */

public class PersonaInteractorImpl implements OperInteractor<Persona> {

    private static final String TAG = PersonaInteractorImpl.class.getSimpleName();
    private PresenterViewListener listener;
    private Retrofit mRestAdapter;
    private RetrofitService mRetrofitService;


    public PersonaInteractorImpl(PresenterViewListener listener) {
        this.listener = listener;
        mRestAdapter = new Retrofit.Builder().baseUrl(RetrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mRetrofitService = mRestAdapter.create(RetrofitService.class);
    }

    @Override
    public void insert(Persona persona) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.addPersona(persona);
        insertCall.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                // Mostrar progreso
                listener.showProgress(false);
                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        ResponseApi responseApi = ResponseApi.fromResponseBody(response.errorBody());
                        error = responseApi.getMensaje();
                        Log.d(TAG, responseApi.getMensaje());
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    listener.setOperationError(error);
                    return;
                }else {
                    ResponseApi responseApi = response.body();
                    String mensaje = responseApi.getMensaje();
                    listener.setOperationSucess(mensaje);
                }

            }
            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                listener.showProgress(false);
                listener.setOperationError(t.getMessage());
            }
        });
    }

    @Override
    public void update(List<Persona> data) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Persona query(long id) {
        return null;
    }

    @Override
    public List<Persona> selectAll() {
        return null;
    }


}
