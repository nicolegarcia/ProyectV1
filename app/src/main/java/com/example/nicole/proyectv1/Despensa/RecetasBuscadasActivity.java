package com.example.nicole.proyectv1.Despensa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicole.proyectv1.DetalleReceta.DetalleRecetaActivity;
import com.example.nicole.proyectv1.R;
import com.example.nicole.proyectv1.Recetas.ParseJsonReceta;
import com.example.nicole.proyectv1.Recetas.Receta;
import com.example.nicole.proyectv1.Recetas.RecetasAdapter;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by SG on 19-01-2016.
 */

public class RecetasBuscadasActivity extends Activity {

    ListView lv;
    ArrayList<Receta> listRecetas = new ArrayList<>();
    ParseJsonReceta parseReceta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetas_buscadas);

        //se lee archivo recetas.json desde /res/raw
        InputStream inputRecetas = getResources().openRawResource(R.raw.recetas);

        try{
            //con parseReceta se crea el ArrayList de recetas listRecetas
            parseReceta = new ParseJsonReceta();
            listRecetas = parseReceta.readJsonStream(inputRecetas);
        } catch (Exception e){
            e.printStackTrace();
        }

        //recibe lista de ingredientes seleccionados desde DespensaActivity.
        ArrayList<String> selectedIngredients = getIntent().getStringArrayListExtra("chkIng");

        //ArrayList que guardará las recetas que coincidan con los ingredientes seleccionados
        ArrayList<Receta> matchReceta = new ArrayList<Receta>();

        for(int i=0; i<listRecetas.size(); i++){

            ArrayList<String> currentIng = listRecetas.get(i).getIngredientes();

            //compara que los ingredientes seleccionados se encuentren en alguna receta
            for(int x=0; x<selectedIngredients.size(); x++){
                for(int y=0; y<currentIng.size(); y++ ){
                    if (selectedIngredients.containsAll(currentIng))
                        if(!matchReceta.contains(listRecetas.get(i)))
                            matchReceta.add(listRecetas.get(i));
                }
            }
        }

        //muestra en listview del layout recetas_buscadas recetas de matchReceta
        lv = (ListView) findViewById(R.id.listRecetasBuscadas);
        RecetasAdapter adapter = new RecetasAdapter(this, matchReceta);
        lv.setAdapter(adapter);

        //Activa reaccion ante elegir un elemento del list view, en este caso ver detalle de una receta.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //cuando un item del list view es seleccionado se abre un nuevo activity
                Intent intent = new Intent(RecetasBuscadasActivity.this, DetalleRecetaActivity.class);
                TextView title = (TextView) view.findViewById(R.id.tituloReceta);

                //envía al siguiente activity el título del item seleccionado
                intent.putExtra("nombre", title.getText().toString());
                startActivity(intent);
            }
        });
    }

}
