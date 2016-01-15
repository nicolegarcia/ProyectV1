package com.example.nicole.proyectv1.DetalleReceta;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nicole.proyectv1.R;
import com.example.nicole.proyectv1.Recetas.ParseJsonReceta;
import com.example.nicole.proyectv1.Recetas.Receta;
import com.example.nicole.proyectv1.DetalleReceta.*;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by SG on 06-01-2016.
 */
public class DetalleRecetaActivity extends Activity{

    ArrayList<Receta> listRecetas = new ArrayList<>();
    ParseJsonReceta parseReceta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_receta);

        //recibe recetas.json archivo
        InputStream inputRecetas = getResources().openRawResource(R.raw.recetas);

        try{
            //con parseReceta se crea el ArrayList de recetas listRecetas
            parseReceta = new ParseJsonReceta();
            listRecetas = parseReceta.readJsonStream(inputRecetas);
        } catch (Exception e){
            e.printStackTrace();
        }

        for(int i=0; i<listRecetas.size(); i++){

            //recibe titulo de receta elegida
            String selectedTitle = getIntent().getStringExtra("nombre");

            String currentTitle = listRecetas.get(i).getTitulo();

            //compara titulo de receta elegida con la lista de recetas en el json
            if(currentTitle.equals(selectedTitle)){

                //muestra detalles de receta que coincide
                mostrarReceta(listRecetas.get(i));
            }
        }
    }

    //a partir de la receta que coincide se actualizan los valores de los objetos que componen detalle_receta.xml
    private void mostrarReceta(Receta receta) {

        TextView title = (TextView) findViewById(R.id.txtTitle);
        TextView proced = (TextView) findViewById(R.id.txtProc);
        ListView listIng = (ListView) findViewById(R.id.lstIngredients);
        ImageView img = (ImageView) findViewById(R.id.imageView2);

        IngredientAdapter adapter = new IngredientAdapter(this, receta.getIngredientes());
        listIng.setAdapter(adapter);

        title.setText(receta.getTitulo());

        proced.setText(receta.getProcedimiento());
        proced.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(getApplicationContext())
                .load(receta.getUrlImage())
                .into(img);
    }
}
