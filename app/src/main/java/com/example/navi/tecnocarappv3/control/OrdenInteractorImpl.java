package com.example.navi.tecnocarappv3.control;

import android.util.Log;

import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Isai on 25/07/2017.
 */

public class OrdenInteractorImpl implements OperInteractor<Orden> {

    private static final String TAG = OrdenInteractorImpl.class.getSimpleName();
    private PresenterViewListener listener;
    private Retrofit mRestAdapter;
    private RetrofitService mRetrofitService;


    public OrdenInteractorImpl(PresenterViewListener listener) {
        this.listener = listener;
        mRestAdapter = new Retrofit.Builder().baseUrl(RetrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mRetrofitService = mRestAdapter.create(RetrofitService.class);
    }


    @Override
    public void post(Orden data) {

    }

    @Override
    public void put(Orden data) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void get(int id) {

        listener.showProgress(true);
        Call<Ordenes> loginCall = mRetrofitService.getOrdenes(id);
        Log.d(TAG, "Obteniendo perfil con clave " + id);
        loginCall.enqueue(new Callback<Ordenes>() {
            @Override
            public void onResponse(Call<Ordenes> call, Response<Ordenes> response) {
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
                        Log.d(TAG, responseApi.getMensaje());
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                Log.i(TAG,"Msg "+response.body().getMensaje());
                DataStore.getInstance().setOrdenList(response.body().getOrden());
                listener.setOperationSucess(new ResponseApi(0, response.body().getMensaje()));
            }

            @Override
            public void onFailure(Call<Ordenes> call, Throwable t) {
                listener.showProgress(false);
                listener.setOperationError(t.getMessage());
            }
        });


    }

    @Override
    public List<Orden> getAll() {
        return null;
    }
}
