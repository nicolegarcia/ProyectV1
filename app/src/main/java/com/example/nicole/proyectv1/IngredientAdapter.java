package com.example.nicole.proyectv1;

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
import com.example.nicole.proyectv1.Recetas.Receta;

/**
 * Created by SG on 06-01-2016.
 */
public class IngredientAdapter extends ArrayAdapter<String> {

    String[] modelItems = null;
    Context context;

    public IngredientAdapter(Context context, String[] resource) {
        super(context, R.layout.row_recetas,resource);
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        name.setText(modelItems[position]);
        cb.setEnabled(false);
        return convertView;
    }
}
