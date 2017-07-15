package com.example.navi.tecnocarappv3.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navi.tecnocarappv3.R;
import com.example.navi.tecnocarappv3.control.Autos;
import com.example.navi.tecnocarappv3.ui.fragment.AutosFragment;

import java.util.ArrayList;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Autos} and makes a call to the
 * specified {@link //OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.ViewHolder> {

    private final AutosFragment.OnLisAutoListener mListener;
    ArrayList<Autos.Auto> autos;

    public AutoAdapter(ArrayList<Autos.Auto> autos, AutosFragment.OnLisAutoListener listener) {
        this.autos = autos;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_auto, parent, false);
        return new ViewHolder(view);
    }

    public void swapData(ArrayList<Autos.Auto> autos){
        this.autos=autos;
        this.notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.marca.setText(autos.get(position).marca );
        holder.modelo.setText(autos.get(position).modelo);
        holder.placa.setText(autos.get(position).placa);
        holder.color.setText(autos.get(position).color);
        holder.anio.setText(autos.get(position).anio+"");
        holder.transmision.setText(autos.get(position).transmision);

        holder.mItem = autos.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnLisItemAutoListener(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (autos==null?0:autos.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView placa;
        public final TextView marca;
        public final TextView modelo;
        public final TextView color;
        public final TextView anio;
        public final TextView transmision;
        public Autos.Auto mItem;
//placa, marca, modelo, color, anio, transmision, cliente_clave
        //que seria lo que hiciste aqui, no?
        //Si solo que en este caso es un adapter por que se trata de una lista pero si seria similar en tucaso soolo ponlo en esa clase que te dije

        public ViewHolder(View view) {
            super(view);
            mView = view;
            placa = (TextView) view.findViewById(R.id.txv_placa);
            marca = (TextView) view.findViewById(R.id.txv_marca);
            modelo = (TextView) view.findViewById(R.id.txv_modelo);
            color = (TextView) view.findViewById(R.id.txv_color);
            anio = (TextView) view.findViewById(R.id.txv_anio);
            transmision = (TextView) view.findViewById(R.id.txv_transmision);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + marca.getText() + "'";
        }
    }
}
