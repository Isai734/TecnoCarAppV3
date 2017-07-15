package com.example.navi.tecnocarappv3.datos;

import com.example.navi.tecnocarappv3.control.Autos;
import com.example.navi.tecnocarappv3.control.Login;
import com.example.navi.tecnocarappv3.control.Personas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * NOta que la peticion que hacemos es del tipo GET es decir que no se enviara nada en el Body de esta, pero que si nos regresara
 * un JSON con los datos del usuario que mandaremos como parametro en el metodo y que este se situara al final de la URL.
 * Si quisieramos enviar datos en el body de la peticion y asi mismo recibir una respuesta del tipo JSON tendriamos que usar
 * el metodo POST y en lugar de utilizar la palabra @Path usariamos @Body.
 */

public interface RetrofitService {
    //Sustituir por URL de server
    public static final String BASE_URL = "http://tecnocar2017.esy.es/ws/";
    @GET("login/{usuario}")
    Call<Login> login(@Path("usuario") String usuario);

    @GET("auto/{id}")
    Call<Autos> getAutos(@Path("id") int claveUsuario);

    @POST("persona")
    Call<Personas> addPersona(@Body Personas persona);

    @GET("persona/{id}")
    Call<Personas> getPersona(@Path("id") int claveUsuario);
}
