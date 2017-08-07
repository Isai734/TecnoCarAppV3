package com.example.navi.tecnocarappv3.model;

import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.control.Autos;
import com.example.navi.tecnocarappv3.control.Cita;
import com.example.navi.tecnocarappv3.control.Citas;
import com.example.navi.tecnocarappv3.control.Login;
import com.example.navi.tecnocarappv3.control.Ordenes;
import com.example.navi.tecnocarappv3.control.Persona;
import com.example.navi.tecnocarappv3.control.Personas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {

    String BASE_URL = "http://tecnocar2017.esy.es/ws/";

    //TODO operaciones CRUD de Login
    @GET("login/{usuario}")
    Call<Login> login(@Path("usuario") String usuario);

    //TODO operaciones CRUD de Persona
    @GET("persona/{id}")
    Call<Personas> getPersona(@Path("id") int claveUsuario);

    @POST("persona")
    Call<ResponseApi> addPersona(@Body Persona persona);

    @PUT("persona/{usuario}")
    Call<ResponseApi> updatePersona(@Path("usuario") int usuario, @Body Persona persona);

    //TODO operaciones CRUD de Autos
    @GET("auto/{id}")
    Call<Autos> getAutos(@Path("id") int claveUsuario);

    @DELETE("auto/{id}")
    Call<ResponseApi> deleteAutos(@Path("id") String claveUsuario);

    @POST("auto")
    Call<ResponseApi> addAuto(@Body Auto auto);

    @PUT("auto/{placa}")
    Call<ResponseApi> updateAuto(@Path("placa") String placa, @Body Auto auto);

    //TODO operaciones CRUD de Ordenes
    @GET("orden/{id}")
    Call<Ordenes> getOrdenes(@Path("id") int claveUsuario);

    //TODO operaciones CRUD de Citas
    @GET("cita/{id}")
    Call<Citas> getCitas(@Path("id") int claveUsuario);

    @DELETE("cita/{id}")
    Call<ResponseApi> deleteCitas(@Path("id") String claveCita);

    @POST("cita")
    Call<ResponseApi> addCitas(@Body Cita cita);

    @PUT("cita/{placa}")
    Call<ResponseApi> updateCitas(@Path("placa") String citaId, @Body Cita cita);

}