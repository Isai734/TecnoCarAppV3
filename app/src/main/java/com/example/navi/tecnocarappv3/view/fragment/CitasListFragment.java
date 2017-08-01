package com.example.navi.tecnocarappv3.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navi.tecnocarappv3.R;

import com.example.navi.tecnocarappv3.control.Cita;
import com.example.navi.tecnocarappv3.control.CitasInteractorImpl;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.MyDialogProgress;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;
import com.example.navi.tecnocarappv3.view.activities.AddCitaActivity;
import com.example.navi.tecnocarappv3.view.adapters.CitasAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListCitasInteractorListener}
 * interface.
 */
public class CitasListFragment extends Fragment implements PresenterViewListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private CitasAdapter adapter;
    private OnListCitasInteractorListener mListener;
    private CitasInteractorImpl interactor;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CitasListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CitasListFragment newInstance(int columnCount) {
        CitasListFragment fragment = new CitasListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new CitasAdapter(null, mListener);
        View view = inflater.inflate(R.layout.fragment_citas, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listCitas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        interactor = new CitasInteractorImpl(this);
        interactor.get(SessionPreferences.get(getContext()).getClaveCliente());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabnuevacita);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddCitaActivity.class));
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListCitasInteractorListener) {
            mListener = (OnListCitasInteractorListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListCitasInteractorListener");
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
            MyDialogProgress.getInstance(getContext()).show("Obteniendo citas");
        else
            MyDialogProgress.getInstance(getContext()).dismiss();
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(getView(), response, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOperationSucess(ResponseApi response) {

        adapter.swapdata(DataStore.getInstance().getCitasList());
        Snackbar.make(getView(), response.getMensaje(), Snackbar.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListCitasInteractorListener {
        // TODO: Update argument type and name
        void onListCitasListener(Cita item);
        void DelteListCitasListener(Cita item);
    }
}
