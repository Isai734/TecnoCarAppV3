package com.example.navi.tecnocarappv3.control;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Isai on 15/07/2017.
 */

public class CitasInteractorImpl implements OperInteractor<Cita> {

    private static final String TAG = CitasInteractorImpl.class.getSimpleName();
    private PresenterViewListener listener;
    private Retrofit mRestAdapter;
    private RetrofitService mRetrofitService;

    public CitasInteractorImpl(PresenterViewListener listener) {
        this.listener = listener;
        mRestAdapter = new Retrofit.Builder().baseUrl(RetrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mRetrofitService = mRestAdapter.create(RetrofitService.class);
    }

    @Override
    public void post(Cita cita) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.addCitas(cita);
        insertCall.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                // Mostrar progreso
                listener.showProgress(false);
                ResponseApi responseApi;
                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {

                        responseApi = ResponseApi.fromResponseBody(response.errorBody());
                        error = responseApi.getMensaje();
                        // error =error+"\nCodigo: "+ response.code();
                        Log.d(TAG, error);
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
                } else {
                    responseApi = response.body();
                    listener.setOperationSucess(responseApi);
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
    public void put(Cita cita) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.updateCitas(cita.getId(),cita);
        insertCall.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                // Mostrar progreso
                listener.showProgress(false);
                ResponseApi responseApi;
                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {

                        responseApi = ResponseApi.fromResponseBody(response.errorBody());
                        error = responseApi.getMensaje();
                        // error =error+"\nCodigo: "+ response.code();
                        Log.d(TAG, error);
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
                } else {
                    responseApi = response.body();
                    listener.setOperationSucess(responseApi);
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
    public void delete(String id) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.deleteCitas(id);
        insertCall.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                // Mostrar progreso
                listener.showProgress(false);
                ResponseApi responseApi;
                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {

                        responseApi = ResponseApi.fromResponseBody(response.errorBody());
                        error = responseApi.getMensaje();
                        // error =error+"\nCodigo: "+ response.code();
                        Log.d(TAG, error);
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    listener.setOperationError(error);

                } else {
                    responseApi = response.body();
                    listener.setOperationSucess(responseApi);
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
    public void get(int id) {
        listener.showProgress(true);
        Call<Citas> loginCall = mRetrofitService.getCitas(id);
        Log.d(TAG, "Obteniendo perfil con clave " + id);
        loginCall.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {
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
                DataStore.getInstance().setCitasList(response.body().getCita());
                listener.setOperationSucess(new ResponseApi(0, response.body().getMensaje()));
            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                listener.showProgress(false);
                listener.setOperationError(t.getMessage());
            }
        });

    }

    @Override
    public List<Cita> getAll() {
        return null;
    }
}
