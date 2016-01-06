package com.example.nicole.proyectv1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicole.proyectv1.Recetas.Receta;
import com.example.nicole.proyectv1.Recetas.RecetasAdapter;

import java.util.ArrayList;

/**
 * Created by SG on 06-01-2016.
 */
public class DetalleReceta extends Activity{

    Receta prueba = new Receta("Titulo 1","primera receta prueba");
    String modelItems [];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_receta);

        //TEST RECETA
        ArrayList<String> ingredientes = new ArrayList<String>();
        ingredientes.add("algo1");
        ingredientes.add("algo2");
        ingredientes.add("algo3");
        ingredientes.add("algo4");
        ingredientes.add("algo5");
        prueba.setIngredientes(ingredientes);
        prueba.setProcedimiento("bla bla bla bla bla bla bla bla");
        prueba.setUrlImage("http://cdn.flaticon.com/png/64/62/62484.png");

        mostrarReceta();
    }

    private void mostrarReceta() {

        TextView title = (TextView) findViewById(R.id.txtTitle);
        TextView proced = (TextView) findViewById(R.id.txtProc);
        ListView listIng = (ListView) findViewById(R.id.lstIngredients);

        modelItems = new String[prueba.getIngredientes().size()];
        for (int i=0; i<prueba.getIngredientes().size(); i++) {
            modelItems[i] = prueba.getIngredientes().get(i);
        }

        IngredientAdapter adapter = new IngredientAdapter(this, modelItems);
        listIng.setAdapter(adapter);

    }
}
