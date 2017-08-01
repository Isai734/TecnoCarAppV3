package com.example.navi.tecnocarappv3.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navi.tecnocarappv3.control.Orden;
import com.example.navi.tecnocarappv3.view.fragment.OrdenListFragment.OnItemOrdenListner;

import java.util.List;

/**
 * specified {@link OnItemOrdenListner}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyOrdenAdapter extends RecyclerView.Adapter<MyOrdenAdapter.ViewHolder> {

    private List<Orden> mValues;
    private final OnItemOrdenListner mListener;

    public MyOrdenAdapter(List<Orden> items, OnItemOrdenListner listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.navi.tecnocarappv3.R.layout.item_servicio, parent, false);
        return new ViewHolder(view);
    }

    public void swapData(List<Orden> items) {
        mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Orden orden = mValues.get(position);

        holder.mItem = orden;
        holder.txv_placa.setText(orden.getPlaca());
        holder.txv_marca.setText(orden.getMarca());
        holder.txv_modelo.setText(orden.getModelo());
        holder.txv_m_obra.setText(orden.getMano_obra());
        holder.txv_costo_servicios.setText(""+(Integer.parseInt(orden.getTotal().trim()) - Integer.parseInt(orden.getMano_obra().trim())));
        holder.txv_total.setText(orden.getTotal());
        holder.txv_mecanico.setText(orden.getNombre() + " " + orden.getApellido_paterno() + " " + orden.getApellido_materno());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnItemOrdenListner(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView txv_placa;
        public final TextView txv_marca;
        public final TextView txv_modelo;
        public final TextView txv_m_obra;
        public final TextView txv_costo_servicios;
        public final TextView txv_total;
        public final TextView txv_mecanico;

        public Orden mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            txv_placa = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_placa);
            txv_marca = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_marca);
            txv_modelo = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_modelo);
            txv_m_obra = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_m_obra);
            txv_costo_servicios = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_costo_servicios);
            txv_total = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_total);
            txv_mecanico = (TextView) view.findViewById(com.example.navi.tecnocarappv3.R.id.txv_mecanico);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txv_mecanico.getText() + "'";
        }
    }
}
