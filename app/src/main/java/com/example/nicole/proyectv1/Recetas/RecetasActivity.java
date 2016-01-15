package com.example.nicole.proyectv1.Recetas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicole.proyectv1.DetalleReceta.DetalleRecetaActivity;
import com.example.nicole.proyectv1.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by SG on 06-01-2016.
 */

public class RecetasActivity extends Activity {
    ArrayList<Receta> prueba = new ArrayList<>();
    ListView lv;
    ArrayList<Receta> listRecetas = new ArrayList<>();
    ParseJsonReceta parseReceta;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetas);

        //recibe recetas.json archivo
        InputStream inputRecetas = getResources().openRawResource(R.raw.recetas);

        try{
            //con parseReceta se crea el ArrayList de recetas listRecetas
            parseReceta = new ParseJsonReceta();
            listRecetas = parseReceta.readJsonStream(inputRecetas);
        } catch (Exception e){
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.listReceta);
        RecetasAdapter adapter = new RecetasAdapter(this, listRecetas);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //cuando un item del list view es seleccionado se abre un nuevo activity
                Intent intent = new Intent(RecetasActivity.this, DetalleRecetaActivity.class);
                TextView title = (TextView) view.findViewById(R.id.tituloReceta);

                //envía al siguiente activity el título del item seleccionado
                intent.putExtra("nombre", title.getText().toString());
                startActivity(intent);
            }
        });
    }

}
