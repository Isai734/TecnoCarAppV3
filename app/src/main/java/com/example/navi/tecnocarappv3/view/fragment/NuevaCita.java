package com.example.navi.tecnocarappv3.view.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.control.AutosInteractorImpl;
import com.example.navi.tecnocarappv3.control.Cita;
import com.example.navi.tecnocarappv3.control.CitasInteractorImpl;
import com.example.navi.tecnocarappv3.control.PersonaInteractorImpl;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.model.RetrofitService;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.MyDialogProgress;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NuevaCita.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NuevaCita extends Fragment implements PresenterViewListener {

    private  String ACTION ="ADD" ;
    private EditText txtFecha;
    private EditText txtDetalle;
    private Button aceptar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Date fechaStar;
    private static final String TAG = "NuevaCita";
    private EditText txtHora;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    private final static int intervaloDeTiempo = 30;
    private Spinner autos;
    private AutosInteractorImpl interactorAutos;
    private CitasInteractorImpl interactorCitas;
    private PersonaInteractorImpl interactorPersona;

    private OnFragmentInteractionListener mListener;

    public NuevaCita() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(com.example.navi.tecnocarappv3.R.layout.fragment_nueva_cita, container, false);
        interactorAutos = new AutosInteractorImpl(this);
        interactorCitas = new CitasInteractorImpl(this);
        interactorPersona = new PersonaInteractorImpl(this);

        txtFecha = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtFecha);
        txtHora = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtHora);
        txtDetalle = (EditText) v.findViewById(com.example.navi.tecnocarappv3.R.id.txtDetalle);
        autos = (Spinner) v.findViewById(com.example.navi.tecnocarappv3.R.id.spinnerAutos);
        aceptar = (Button) v.findViewById(com.example.navi.tecnocarappv3.R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
saveData();
            }
        });

        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                int mes = cal.get(Calendar.MONTH);
                int anio = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, anio, mes, dia);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                Log.d(TAG, "onDateSet:" + dayOfMonth + month + year);
                String date1 = dayOfMonth + "/" + month + "/" + year;
                txtFecha.setText(date1);

                String input = dayOfMonth + "/" + (month) + "/" + year;
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
                fechaStar=null;
                try {
                    fechaStar = parser.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hora = cal.get(Calendar.HOUR_OF_DAY);
                int minutos = cal.get(Calendar.MINUTE);

                TimePickerDialog timedialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mTimeSetListener, hora, minutos, false);
                timedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timedialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                txtHora.setText(time);
            }
        };

        interactorAutos.get(SessionPreferences.get(getContext()).getClaveCliente());
        interactorPersona.get(SessionPreferences.get(getContext()).getClaveCliente());
        return v;
    }

    public void saveData(){
        ACTION="ADDs";
        interactorCitas.post(new Cita(
                /**
                 *  public String id;
                 public String title;
                 public String body;
                 public String url;
                 @SerializedName("class")
                 public String clase;
                 public String start;
                 public String end;
                 public String inicio_normal;
                 public String final_normal;
                 public String auto_placa;
                 public String empresa_clave;
                 public String cliente_clave;
                 public String status;
                 public String placa;
                 public String marca;
                 public String modelo;
                 public String color;
                 public String anio;
                 public String transmision;
                 */
                null
                ,DataStore.getInstance().getPersona().getNombre()+" "+DataStore.getInstance().getPersona().getApellido_paterno()+" "+DataStore.getInstance().getPersona().getApellido_materno()
                ,txtDetalle.getText().toString()
                , null
                ,"event-important"
                ,fechaStar.getTime()+""
                ,fechaStar.getTime()+""
                ,txtFecha.getText().toString()+" "+txtHora.getText().toString()
                ,txtFecha.getText().toString()+" "+txtHora.getText().toString()
                ,DataStore.getInstance().getAutoList().get(autos.getSelectedItemPosition()).getPlaca()
                ,null
                ,SessionPreferences.get(getContext()).getClaveCliente()+""
                ,"ESPERA"
                ,null
                ,null
                ,null
                ,null
                ,null
                ,null
        ));
    }

    public List<String> createDataSpinner() {
        List<String> list = new LinkedList<>();
        for (Auto auto : DataStore.getInstance().getAutoList()) {
            list.add("Placa : " + auto.getPlaca() + " - " + "Marca : " + auto.getMarca() + " - " + "Color : " + auto.getColor());
        }
        return list;
    }


    // TODO: Rename method, put argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress(boolean show) {
        if (show)
            MyDialogProgress.getInstance(getContext()).show("Realizando Operaciones");
        else
            MyDialogProgress.getInstance(getContext()).dismiss();
        Log.i(TAG,"Show ptogress : "+show);
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(getView(), response, Snackbar.LENGTH_LONG).show();
        Log.i(TAG,"setOperation Error : "+response);
    }

    @Override
    public void setOperationSucess(ResponseApi response) {
        Log.i(TAG,"setOperation Sucess : "+response.getMensaje()+" action : "+ACTION);
        if(ACTION=="ADD"){


            Snackbar.make(getView(), "Agende su cita", Snackbar.LENGTH_LONG).show();
            if (createDataSpinner().isEmpty()) {
                Toast.makeText(getContext(), "Datos insuficientes \nAgregue autos primero o Contacte al administrador", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, createDataSpinner());
            autos.setAdapter(adapter);

        }else {
            if(ACTION=="ADDs"){
                Snackbar.make(getView(), response.getMensaje(), Snackbar.LENGTH_LONG).show();
                getActivity().finish();
            }
            Snackbar.make(getView(), response.getMensaje(), Snackbar.LENGTH_LONG).show();
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
