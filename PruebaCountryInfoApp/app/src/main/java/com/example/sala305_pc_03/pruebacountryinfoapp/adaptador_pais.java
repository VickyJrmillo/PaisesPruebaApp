package com.example.sala305_pc_03.pruebacountryinfoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adaptador_pais extends ArrayAdapter<pais> {
    public adaptador_pais(Context context, ArrayList<pais> datos) {
        super(context, R.layout.ly_listar_paises, datos);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_listar_paises, null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.LblNombre);
        lblTitulo.setText(getItem(position).getNombre());

        TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblAlpha2_code);
        lblSubtitulo.setText(getItem(position).getAlpha2());

        TextView lblFechaPub = (TextView) item.findViewById(R.id.LblAlpha3_code);
        lblFechaPub.setText(getItem(position).getAlpha3());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgBandera);
        Glide.with(this.getContext())
                .load(getItem(position).getBandera())
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
        return (item);

    }

}
