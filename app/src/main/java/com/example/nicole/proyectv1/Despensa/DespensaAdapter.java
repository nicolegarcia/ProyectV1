package com.example.nicole.proyectv1.Despensa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.example.nicole.proyectv1.R;

import java.util.ArrayList;


/**
 * Created by SG on 05-01-2016.
 */

//Clase adpatador para mostrar Ingredientes en modo de checkbox
//A partir de un ArrayList<String> con los ingredientes se hace un checkbox.setText para mostrar cada nombre
public class DespensaAdapter extends ArrayAdapter<String> {

    ArrayList<String> modelItems = null;
    Context context;
    CheckBox cb;
    ArrayList<String> chkList;

    public DespensaAdapter(Context context, ArrayList<String> resource) {
        super(context, R.layout.row_despensa,resource);
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row_despensa, parent, false);
        cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        cb.setText(modelItems.get(position));

        return convertView;
    }

}
