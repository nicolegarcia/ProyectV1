package com.example.nicole.proyectv1.Despensa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nicole.proyectv1.R;

import java.util.ArrayList;

/**
 * Created by SG on 05-01-2016.
 */
public class DespensaActivity extends Activity {

    ListView lv;
    ArrayList<String> listIngredientes = new ArrayList<String>();
    DespensaAdapter adapter;
    ArrayList<String> selectedIngredients;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despensa);

        //Inicializar lista de ingredientes
        listIngredientes.add("huevos");
        listIngredientes.add("harina");
        listIngredientes.add("cebolla");
        listIngredientes.add("papas");
        listIngredientes.add("azucar");
        listIngredientes.add("leche");
        listIngredientes.add("vainilla");
        listIngredientes.add("manjar");

        mostrarIngredientes();
        addIngrediente();
        buscarReceta();
    }

    //Agrega Ingrediente a lista
    private void addIngrediente(){
        Button agregar = (Button) findViewById(R.id.btnAdd);
        final EditText text = (EditText) findViewById(R.id.addIngText);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIng = text.getText().toString();
                //verifica que se haya ingresado texto válido
                if(newIng.equals("") | newIng.contains("1")| newIng.contains("2")| newIng.contains("3")){
                    Toast.makeText(getApplicationContext(),"Ingrese Ingrediente válido", Toast.LENGTH_SHORT).show();
                }else {
                    listIngredientes.add(newIng);
                    text.setText("");
                }
            }
        });
        mostrarIngredientes();
    }
    //muestra lista de ingredientes en un list view adaptado por DespensaAdapter
    private void mostrarIngredientes(){

        lv = (ListView) findViewById(R.id.listView1);
        for (int i=0; i<listIngredientes.size(); i++){
            adapter = new DespensaAdapter(this, listIngredientes);
            lv.setAdapter(adapter);
        }
    }

    //a partir de se los ingredientes seleccionados, busca recetas que coincidan
    private void buscarReceta(){
        selectedIngredients = new ArrayList<String>();
        Button btnBuscar = (Button) findViewById(R.id.btn_Buscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al apretar el boton buscar receta guarda el texto de los checkbox que han sido checkeados
                CheckBox cb;
                for (int i = 0; i<lv.getChildCount();i++)
                {
                    cb = (CheckBox)lv.getChildAt(i).findViewById(R.id.checkBox1);
                    if(cb.isChecked())
                    {
                        selectedIngredients.add(cb.getText().toString());
                    }
                }
                //la lista de los checkbox checkeados es enviada al siguiente activity
                Intent intent = new Intent(DespensaActivity.this, RecetasBuscadasActivity.class);
                intent.putExtra("chkIng", selectedIngredients);
                startActivity(intent);
            }
        });
    }


}
