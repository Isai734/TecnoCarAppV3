package com.example.navi.tecnocarappv3.model;

import com.example.navi.tecnocarappv3.control.Autos;
import com.example.navi.tecnocarappv3.control.Login;
import com.example.navi.tecnocarappv3.control.Persona;
import com.example.navi.tecnocarappv3.control.Personas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    String BASE_URL = "http://192.168.43.203/tecnocar/ws/";

    @GET("login/{usuario}")
    Call<Login> login(@Path("usuario") String usuario);

    @GET("auto/{id}")
    Call<Autos> getAutos(@Path("id") int claveUsuario);

    @POST("persona")
    Call<ResponseApi> addPersona(@Body Persona persona);

    @GET("persona/{id}")
    Call<Personas> getPersona(@Path("id") int claveUsuario);
}
