package com.example.navi.tecnocarappv3.ui.fragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.util.CircularArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.navi.tecnocarappv3.R;

import java.sql.BatchUpdateException;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NuevaCita.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NuevaCita extends Fragment {

    private EditText txtFecha;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "NuevaCita";
    private EditText txtHora;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    private final static int intervaloDeTiempo = 30;
    private Spinner autos;

    private OnFragmentInteractionListener mListener;

    public NuevaCita() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nueva_cita, container, false);

        txtFecha = (EditText) v.findViewById(R.id.txtFecha);
        txtHora = (EditText) v.findViewById(R.id.txtHora);
        autos = (Spinner) v.findViewById(R.id.spinnerAutos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.autos, android.R.layout.simple_spinner_item);
        autos.setAdapter(adapter);


        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                int mes = cal.get(Calendar.MONTH);
                int anio = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,anio,mes,dia);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                Log.d(TAG,"onDateSet:"+dayOfMonth+month+year);
                String date = dayOfMonth + "/" + month + "/" + year;
                txtFecha.setText(date);
            }
        };

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hora  = cal.get(Calendar.HOUR_OF_DAY);
                int minutos = cal.get(Calendar.MINUTE);

                TimePickerDialog timedialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,mTimeSetListener,hora,minutos,false);
                timedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timedialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay+":"+minute;
                txtHora.setText(time);
            }
        };

        return v;
    }



    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
