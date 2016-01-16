package com.example.nicole.proyectv1.Despensa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nicole.proyectv1.R;
import com.example.nicole.proyectv1.Usuario;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by SG on 05-01-2016.
 */
public class DespensaActivity extends Activity {

    ListView lv;
    ArrayList<String> modelItems;
    ParseJsonUsuarios parseUser;
    ArrayList<Usuario> listUser;
    Profile profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despensa);
        InputStream inputUser = getResources().openRawResource(R.raw.usuarios);

        try{
            //con parseReceta se crea el ArrayList de recetas listRecetas
            parseUser = new ParseJsonUsuarios();
            listUser = parseUser.readJsonStream(inputUser);
        } catch (Exception e){
            e.printStackTrace();
        }

        mostrarIngredientes();
        addIngrediente();
    }

    private void addIngrediente(){

        final InputStream inputUser = getResources().openRawResource(R.raw.usuarios);

        Button agregar = (Button) findViewById(R.id.btnAdd);
        final EditText text = (EditText) findViewById(R.id.addIngText);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIng = text.getText().toString();
                JSONArray arr = null;
                try {
                    arr = new JSONArray(inputUser.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (arr != null) {
                    for(int i = 0; i < arr.length(); i++){

                        JSONObject jsonObj = null; // get the josn object
                        try {
                            jsonObj = (JSONObject)arr.get(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (jsonObj != null) {
                                if(jsonObj.getString("name").equals(Profile.getCurrentProfile().getName())){ // compare for the key-value
                                    ((JSONObject)arr.get(i)).getJSONArray("ingredient").put(newIng); // put the new value for the key
                                    try{
                                        FileOutputStream fileout = new FileOutputStream("/res/raw/usuarios.json");
                                        ObjectOutputStream out = new ObjectOutputStream(fileout);
                                        out.writeObject(arr.toString());
                                    }catch (Exception e){
                                        Toast.makeText(getApplicationContext(), "no se agregó ingrediente", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                text.setText("");
            }
        });
    }

    private void mostrarIngredientes(){

        lv = (ListView) findViewById(R.id.listView1);
        for (int i=0; i<listUser.size(); i++){
            if(profile.getCurrentProfile().getName().equals(listUser.get(i).getName())){
                DespensaAdapter adapter = new DespensaAdapter(this, listUser.get(i).getIngredientesUsuario());
                lv.setAdapter(adapter);
            }
        }
    }
}
