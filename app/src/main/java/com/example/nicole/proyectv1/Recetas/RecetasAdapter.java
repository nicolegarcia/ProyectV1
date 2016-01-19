package com.example.nicole.proyectv1.Recetas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nicole.proyectv1.R;

import java.util.ArrayList;

/**
 * Created by SG on 06-01-2016.
 */

//Clase que permite mostrar recetas en el listView de Recetas, cada fila está dada por row_recetas

public class RecetasAdapter extends ArrayAdapter<Receta> {

    ArrayList<Receta> listRecetas = null;
    Context context;

    public RecetasAdapter(Context context, ArrayList<Receta> resource) {
        super(context, R.layout.row_recetas, resource);
        this.context = context;
        this.listRecetas = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row_recetas, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.tituloReceta);
        TextView description = (TextView) convertView.findViewById(R.id.descReceta);

        title.setText(listRecetas.get(position).getTitulo());
        description.setText(listRecetas.get(position).getDescripcion());
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
        Glide.with(context)
                .load(listRecetas.get(position).getUrlImage())
                .into(iv);
        return convertView;
    }

}
