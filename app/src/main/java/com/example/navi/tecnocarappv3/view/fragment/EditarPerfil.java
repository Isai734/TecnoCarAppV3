package com.example.navi.tecnocarappv3.view.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.control.Persona;
import com.example.navi.tecnocarappv3.control.PersonaInteractorImpl;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.MyDialogProgress;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditarPerfil.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditarPerfil extends Fragment implements PresenterViewListener {

    private OnFragmentInteractionListener mListener;

    public EditarPerfil() {
        // Required empty public constructor
    }

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
    private PersonaInteractorImpl personaInteractor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        personaInteractor = new PersonaInteractorImpl(this);

        userId = (EditText) v.findViewById(R.id.user_id);
        pass = (EditText) v.findViewById(R.id.password);
        pass2 = (EditText) v.findViewById(R.id.password2);
        userName = (EditText) v.findViewById(R.id.user_name);
        userAP = (EditText) v.findViewById(R.id.user_ap);
        userAM = (EditText) v.findViewById(R.id.user_am);
        userPhone = (EditText) v.findViewById(R.id.user_telefono);
        userAddress = (EditText) v.findViewById(R.id.user_direccion);
        userEmial = (EditText) v.findViewById(R.id.user_email);
        userRfc = (EditText) v.findViewById(R.id.user_rfc);
        sigUpBtn = (Button) v.findViewById(R.id.email_sign_in_button);

        sigUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFields())
                    return;

                personaInteractor.put(new Persona(
                        SessionPreferences.get(getContext()).getClaveCliente(),
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
        setData();
        return v;
    }

    public void setData() {
        Persona persona = DataStore.getInstance().getPersona();
        userId.setText(SessionPreferences.get(getContext()).getUserCliente());
        pass.setText(SessionPreferences.get(getContext()).getPassCliente());
        pass2.setText(SessionPreferences.get(getContext()).getPassCliente());
        userName.setText(persona.nombre);
        userAP.setText(persona.apellido_paterno);
        userAM.setText(persona.apellido_materno);
        userPhone.setText(persona.telefono);
        userAddress.setText(persona.direccion);
        userEmial.setText(persona.email);
        userRfc.setText(persona.getRfc());
    }

    // TODO: Rename method, put argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress(boolean show) {
        if (show)
            MyDialogProgress.getInstance(getContext()).show("Actualizando perfil");
        else
            MyDialogProgress.getInstance(getContext()).dismiss();
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(getView().findViewById(R.id.email_sign_in_button), response, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOperationSucess(ResponseApi response) {
        Toast.makeText(getContext(), response.getMensaje(), Toast.LENGTH_LONG).show();
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
