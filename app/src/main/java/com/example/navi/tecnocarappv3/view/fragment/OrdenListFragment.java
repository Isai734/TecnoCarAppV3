package com.example.navi.tecnocarappv3.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navi.tecnocarappv3.control.Orden;
import com.example.navi.tecnocarappv3.control.OrdenInteractorImpl;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.model.ResponseApi;
import com.example.navi.tecnocarappv3.prefs.SessionPreferences;
import com.example.navi.tecnocarappv3.view.MyDialogProgress;
import com.example.navi.tecnocarappv3.view.PresenterViewListener;
import com.example.navi.tecnocarappv3.view.adapters.MyOrdenAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnItemOrdenListner}
 * interface.
 */
public class OrdenListFragment extends Fragment implements PresenterViewListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnItemOrdenListner mListener;
    private OrdenInteractorImpl interactor;
    private MyOrdenAdapter adapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrdenListFragment() {
    }


    public static OrdenListFragment newInstance(int columnCount) {
        OrdenListFragment fragment = new OrdenListFragment();
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
        interactor = new OrdenInteractorImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.navi.tecnocarappv3.R.layout.fragment_orden_list, container, false);
        adapter=new MyOrdenAdapter(null, mListener);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }
        interactor.get(SessionPreferences.get(getContext()).getClaveCliente());
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemOrdenListner) {
            mListener = (OnItemOrdenListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemOrdenListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress(boolean show) {
        if(show)
            MyDialogProgress.getInstance(getContext()).show("Obteniendo Ordenes de Servicios");
        else
            MyDialogProgress.getInstance(getContext()).dismiss();
    }

    @Override
    public void setOperationError(String response) {
        Snackbar.make(getView(),response,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setOperationSucess(ResponseApi response) {
        Snackbar.make(getView(),response.getMensaje(),Snackbar.LENGTH_LONG).show();
        adapter.swapData(DataStore.getInstance().getOrdenList());
    }


    public interface OnItemOrdenListner {
        // TODO: Update argument type and name
        void OnItemOrdenListner(Orden item);
    }
}
