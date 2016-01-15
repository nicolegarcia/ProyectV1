package com.example.nicole.proyectv1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nicole.proyectv1.Despensa.Ingrediente;
import com.example.nicole.proyectv1.Despensa.DespensaAdapter;

import java.util.ArrayList;

/**
 * Created by SG on 05-01-2016.
 */
public class DespensaActivity extends Activity {

    ArrayList<String> ingredientes = new ArrayList<String>();
    ListView lv;
    Ingrediente[] modelItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despensa);

        irAddIngrediente();
        mostrarIngredientes();
    }

    private void irAddIngrediente(){

        Button agregar = (Button) findViewById(R.id.btnAdd);
        final EditText text = (EditText) findViewById(R.id.addIngText);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientes.add(text.getText().toString());
                text.setText("");
                mostrarIngredientes();
            }
        });
    }
    private  void mostrarIngredientes(){

        lv = (ListView) findViewById(R.id.listReceta);

        modelItems = new Ingrediente[ingredientes.size()];
        for (int i=0; i<ingredientes.size(); i++) {
            modelItems[i] = new Ingrediente(ingredientes.get(i));
        }

        DespensaAdapter adapter = new DespensaAdapter(this, modelItems);
        lv.setAdapter(adapter);
    }
}
