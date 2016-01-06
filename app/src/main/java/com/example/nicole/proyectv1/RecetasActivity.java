package com.example.nicole.proyectv1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nicole.proyectv1.Recetas.Receta;
import com.example.nicole.proyectv1.Recetas.RecetasAdapter;

import java.util.ArrayList;

/**
 * Created by SG on 06-01-2016.
 */

public class RecetasActivity extends Activity{
    ArrayList<Receta> prueba = new ArrayList<>();;
    ListView lv;
    Receta[] modelItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetas);
        prueba.add(new Receta("receta1", "descripcion1"));
        prueba.add(new Receta("receta2", "descripcion2"));
        prueba.add(new Receta("receta3", "descripcion3"));
        prueba.add(new Receta("receta4", "descripcion4"));

        mostrarRecetas();
    }

    private void mostrarRecetas() {

        lv = (ListView) findViewById(R.id.listView1);

        modelItems = new Receta[prueba.size()];
        for (int i=0; i<prueba.size(); i++) {
            modelItems[i] = prueba.get(i);
        }

        RecetasAdapter adapter = new RecetasAdapter(this, modelItems);
        lv.setAdapter(adapter);
    }


}
