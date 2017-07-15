package com.example.navi.tecnocarappv3.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.navi.tecnocarappv3.control.Login;

/**
 * Preferencias de Login== Las preferncias se guardan para que no se consulte el servicio web cada vez que se inicie sesion
 * por seguridad se sugiere que cada vez que se consume un servicio se verifique el usuario que consume el web service este activo
 */
public class SessionPreferences {

    public static final String PREFS_USER = "PREFS_USER";
    public static final String PREF_PASS = "PREF_PASS";
    public static final String PREF_CLIENTE_CLV = "PREF_CLIENTE_CLV";


    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPreferences INSTANCE;

    public static SessionPreferences get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPreferences(context);
        }
        return INSTANCE;
    }

    private SessionPreferences(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_USER, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREFS_USER, null));
    }

    public int getClaveCliente(){
       return mPrefs.getInt(PREF_CLIENTE_CLV, 0);
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void saveLogin(Login login) {
        if (login != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREFS_USER, login.getUsuario());
            editor.putString(PREF_PASS, login.getContrasenia());
            editor.putInt(PREF_CLIENTE_CLV, login.getPersonaId());
            editor.apply();

            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREFS_USER, null);
        editor.putString(PREF_PASS, null);
        editor.putInt(PREF_CLIENTE_CLV, 0);
        editor.apply();
    }
}
