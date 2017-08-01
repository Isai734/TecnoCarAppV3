package com.example.navi.tecnocarappv3.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.navi.tecnocarappv3.R;

import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.control.Cita;
import com.example.navi.tecnocarappv3.model.DataStore;
import com.example.navi.tecnocarappv3.view.fragment.CitasListFragment.OnListCitasInteractorListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.example.navi.tecnocarappv3.control.Cita} and makes a call to the
 * specified {@link OnListCitasInteractorListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {

    private List<Cita> mValues;
    private final OnListCitasInteractorListener mListener;

    public CitasAdapter(List<Cita> items, OnListCitasInteractorListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cita, parent, false);
        return new ViewHolder(view);
    }

    public void swapdata(List<Cita> items){
        mValues = items;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cita cita = mValues.get(position);

        holder.mItem = cita;
        holder.txv_fecha.setText(cita.getInicio_normal());
        holder.txv_placa.setText(cita.getAuto_placa());
        holder.txv_marca.setText(cita.getMarca());
        holder.txv_color.setText(cita.getColor());
        holder.txv_anio.setText(cita.getAnio());
        holder.txv_transmision.setText(cita.getTransmision());
        holder.txv_detalle.setText(cita.getBody());
        holder.btn_eliminar_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.DelteListCitasListener(holder.mItem);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListCitasListener(holder.mItem);
                }
            }
        });
    }

    public Auto getAuto(String placa) {
        for (Auto auto : DataStore.getInstance().getAutoList()) {
            if (placa.equals(auto.getPlaca()))
                return auto;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txv_fecha;
        public final TextView txv_placa;
        public final TextView txv_marca;
        public final TextView txv_color;
        public final TextView txv_anio;
        public final TextView txv_transmision;
        public final TextView txv_detalle;
        public final Button btn_eliminar_cita;
        public Cita mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txv_fecha = (TextView) view.findViewById(R.id.txv_fecha);
            txv_placa = (TextView) view.findViewById(R.id.txv_placa);
            txv_marca = (TextView) view.findViewById(R.id.txv_marca);
            txv_color = (TextView) view.findViewById(R.id.txv_color);
            txv_anio = (TextView) view.findViewById(R.id.txv_anio);
            txv_transmision = (TextView) view.findViewById(R.id.txv_transmision);
            txv_detalle = (TextView) view.findViewById(R.id.txv_detalle);
            btn_eliminar_cita = (Button) view.findViewById(R.id.btn_eliminar_cita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txv_detalle.getText() + "'";
        }
    }
}
