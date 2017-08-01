package com.example.navi.tecnocarappv3.control;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;

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

public class AutosInteractorImpl implements OperInteractor<Auto> {

    private static final String TAG = AutosInteractorImpl.class.getSimpleName();
    private PresenterViewListener listener;
    private Retrofit mRestAdapter;
    private RetrofitService mRetrofitService;









    public AutosInteractorImpl(PresenterViewListener listener) {
        this.listener = listener;
        mRestAdapter = new Retrofit.Builder().baseUrl(RetrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mRetrofitService = mRestAdapter.create(RetrofitService.class);
    }

    @Override
    public void post(Auto data) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.addAuto(data);
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
    public void put(Auto data) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.updateAuto(data.getPlaca(),data);
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
        Call<ResponseApi> insertCall = mRetrofitService.deleteAutos(id);
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
    public void get(int id) {
        Call<Autos> loginCall = mRetrofitService.getAutos(id);

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
                listener.setOperationSucess(new ResponseApi(0,"Datos cargados"));
            }

            @Override
            public void onFailure(Call<Autos> call, Throwable t) {
                listener.setOperationError("Error al cargar datos");

            }
        });
    }

    @Override
    public List<Auto> getAll() {
        return null;
    }
}
