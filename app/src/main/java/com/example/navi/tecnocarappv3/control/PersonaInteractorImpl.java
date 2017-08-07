package com.example.navi.tecnocarappv3.control;

import android.util.Log;

import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import okio.Buffer;
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
    public void post(Persona persona) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.addPersona(persona);
        insertCall.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                // Mostrar progreso
                listener.showProgress(false);
                ResponseApi responseApi;
                // Procesar errores
               // Log.d(TAG, bodyToString(response.errorBody()));
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        //Log.d(TAG,"Body "+ bodyToString(response.errorBody()));
                        responseApi = ResponseApi.fromResponseBody(response.errorBody());
                        assert responseApi != null;
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

    private String bodyToString(final ResponseBody request) {
        try {
            final ResponseBody copy = request;
            final Buffer buffer = new Buffer();
            byte b[] = new byte[0];
            if (copy != null) {
                buffer.readFrom(copy.byteStream());
            } else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    @Override
    public void put(Persona data) {
        listener.showProgress(true);
        Call<ResponseApi> insertCall = mRetrofitService.updatePersona(data.getClave(),data);
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

    }

    @Override
    public void get(int id) {
        listener.showProgress(true);
        Call<Personas> loginCall = mRetrofitService.getPersona(id);
        Log.d(TAG, "Obteniendo perfil con clave " + id);
        loginCall.enqueue(new Callback<Personas>() {
            @Override
            public void onResponse(Call<Personas> call, Response<Personas> response) {
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
                if (response.body().getPersona().isEmpty()) {
                    listener.setOperationError("No hay datos");
                    return;
                }
                DataStore.getInstance().setPersona(response.body().getPersona().get(0));
                listener.setOperationSucess(new ResponseApi(0,"Perfil obtenido"));

            }

            @Override
            public void onFailure(Call<Personas> call, Throwable t) {
                listener.showProgress(false);
                listener.setOperationError(t.getMessage());
            }
        });
    }

    @Override
    public List<Persona> getAll() {
        return null;
    }


}
