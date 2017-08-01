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

import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.control.AutosInteractorImpl;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutoEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AutoEditFragment extends Fragment implements PresenterViewListener {

    private OnFragmentInteractionListener mListener;
    private AutosInteractorImpl interactor;
    private int pos = -1;

    private EditText txtMatricula;
    private EditText txtMarca;
    private EditText txtModelo;
    private EditText txtColor;
    private EditText txtAnio;
    private EditText txtTransmision;
    private Button btnNuevoAuto;

    public AutoEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(com.example.navi.tecnocarappv3.R.layout.fragment_nuevo_auto, container, false);
        interactor = new AutosInteractorImpl(this);
        if (getArguments() != null) {
            pos = getArguments().getInt("pos");
        }

        txtMatricula = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtMatricula);
        txtMarca = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtMarca);
        txtModelo = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtModelo);
        txtColor = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtColor);
        txtAnio = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtAnio);
        txtTransmision = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtTransmision);

        btnNuevoAuto = (Button) v.findViewById(com.example.navi.tecnocarappv3.R.id.button_nuevoAuto);
        btnNuevoAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFields())
                    return;
                interactor.put(new Auto(
                        SessionPreferences.get(getContext()).getClaveCliente(),
                        txtMatricula.getText().toString(),
                        txtMarca.getText().toString(),
                        txtModelo.getText().toString(),
                        txtColor.getText().toString(),
                        Integer.parseInt(txtAnio.getText().toString()),
                        txtTransmision.getText().toString()
                ));
            }
        });
        setData();
        return v;
    }

    // TODO: Rename method, put argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public boolean validateFields() {

        if (txtMatricula.getText().toString().isEmpty()) {
            txtMatricula.setError("Campo vacio");
            return false;
        }
        if (txtMarca.getText().toString().isEmpty()) {
            txtMarca.setError("Campo vacio");
            return false;
        }
        if (txtModelo.getText().toString().isEmpty()) {
            txtModelo.setError("Campo vacio");
            return false;
        }
        if (txtColor.getText().toString().isEmpty()) {
            txtColor.setError("Campo vacio");
            return false;
        }
        if (txtAnio.getText().toString().isEmpty()) {
            txtAnio.setError("Campo vacio");
            return false;
        }
        if (txtTransmision.getText().toString().isEmpty()) {
            txtTransmision.setError("Campo vacio");
            return false;
        }

        return true;
    }

    public void setData() {
        if (pos == -1) {
            getActivity().finish();
        }
        Auto auto = DataStore.getInstance().getAutoList().get(pos);
        txtMatricula.setText(auto.getPlaca());
        txtMarca.setText(auto.getMarca());
        txtModelo.setText(auto.getModelo());
        txtColor.setText(auto.getColor());
        txtAnio.setText(auto.getAnio()+"");
        txtTransmision.setText(auto.getTransmision());
        btnNuevoAuto.setText("Modificar");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(getView().findViewById(com.example.navi.tecnocarappv3.R.id.button_nuevoAuto), response, Snackbar.LENGTH_LONG).show();
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
