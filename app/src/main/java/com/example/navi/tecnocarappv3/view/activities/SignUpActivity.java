package com.example.navi.tecnocarappv3.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.control.Login;
import com.example.navi.tecnocarappv3.control.Persona;
import com.example.navi.tecnocarappv3.control.PersonaInteractorImpl;
import com.example.navi.tecnocarappv3.control.Personas;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;
import com.example.navi.tecnocarappv3.view.Principal;

public class SignUpActivity extends AppCompatActivity implements PresenterViewListener {

    private EditText userId;
    private EditText pass;
    private EditText pass2;
    private EditText userName;
    private EditText userAP;
    private EditText userAM;
    private EditText userPhone;
    private EditText userAddress;
    private EditText userEmial;
    private EditText userRfc;
    private Button sigUpBtn;
    private ProgressDialog progressBar;

    private PersonaInteractorImpl personaInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        personaInteractor = new PersonaInteractorImpl(this);
        initProgressBar();

        userId = (EditText) findViewById(R.id.user_id);
        pass = (EditText) findViewById(R.id.password);
        pass2 = (EditText) findViewById(R.id.password2);
        userName = (EditText) findViewById(R.id.user_name);
        userAP = (EditText) findViewById(R.id.user_ap);
        userAM = (EditText) findViewById(R.id.user_am);
        userPhone = (EditText) findViewById(R.id.user_telefono);
        userAddress = (EditText) findViewById(R.id.user_direccion);
        userEmial = (EditText) findViewById(R.id.user_email);
        userRfc = (EditText) findViewById(R.id.user_rfc);
        sigUpBtn = (Button) findViewById(R.id.email_sign_in_button);

        sigUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFields())
                    return;
                personaInteractor.insert(new Persona(
                        0,
                        userName.getText().toString(),
                        userAP.getText().toString(),
                        userAM.getText().toString(),
                        userPhone.getText().toString(),
                        userAddress.getText().toString(),
                        userEmial.getText().toString(),
                        null,
                        userRfc.getText().toString()
                        , "null",
                        "CLIENTE",
                        "null",
                        userId.getText().toString(),
                        pass.getText().toString()
                        , "null"
                ));
            }
        });

    }


    @Override
    public void showProgress(boolean show) {
        if (show)
            progressBar.show();
        else
            progressBar.dismiss();
    }

    public boolean validateFields() {
        if (!pass.getText().toString().equals(pass2.getText().toString())) {
            pass2.setError("Contraseñas no coinciden");
            return false;
        }
        if (pass.getText().toString().isEmpty()) {
            pass.setError("Campo vacio");
            return false;
        }
        if (userName.getText().toString().isEmpty()) {
            userName.setError("Campo vacio");
            return false;
        }
        if (userAP.getText().toString().isEmpty()) {
            userAP.setError("Campo vacio");
            return false;
        }
        if (userAM.getText().toString().isEmpty()) {
            userAM.setError("Campo vacio");
            return false;
        }
        if (userPhone.getText().toString().isEmpty()) {
            userPhone.setError("Campo vacio");
            return false;
        }
        if (userAddress.getText().toString().isEmpty()) {
            userAddress.setError("Campo vacio");
            return false;
        }
        if (userEmial.getText().toString().isEmpty()) {
            userEmial.setError("Campo vacio");
            return false;
        }
        if (userRfc.getText().toString().isEmpty()) {
            userRfc.setError("Campo vacio");
            return false;
        }
        return true;
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(findViewById(R.id.email_sign_in_button), response, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOperationSucess(ResponseApi response) {
        Toast.makeText(this, response.getMensaje(), Toast.LENGTH_LONG).show();
        SessionPreferences.get(this).saveLogin(new Login(response.getEstado(), userId.getText().toString(), pass.getText().toString()));
        startActivity(new Intent(this, Principal.class));
        finish();
    }

    public void initProgressBar() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMessage("Agregando persona");
        progressBar.setMax(100);
    }
}
