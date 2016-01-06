package com.example.nicole.proyectv1.Recetas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nicole.proyectv1.Despensa.Ingrediente;
import com.example.nicole.proyectv1.R;

/**
 * Created by SG on 06-01-2016.
 */
public class RecetasAdapter extends ArrayAdapter<Receta> {

    Receta[] modelItems = null;
    Context context;

    public RecetasAdapter(Context context, Receta[] resource) {
        super(context, R.layout.row_recetas,resource);
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row_recetas, parent, false);
        TextView title = (TextView) convertView.findViewById(R.id.tituloReceta);
        TextView description = (TextView) convertView.findViewById(R.id.descReceta);
        title.setText(modelItems[position].getTitulo());
        description.setText(modelItems[position].getDescripcion());
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
        Glide.with(context)
                .load("http://cdn.flaticon.com/png/64/62/62484.png")
                .into(iv);
        return convertView;
    }

}
