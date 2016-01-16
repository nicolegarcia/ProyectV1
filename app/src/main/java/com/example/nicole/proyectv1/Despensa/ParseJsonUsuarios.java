package com.example.nicole.proyectv1.Despensa;

import android.util.JsonReader;

import com.example.nicole.proyectv1.Usuario;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by nicole on 15-01-2016.
 */
public class ParseJsonUsuarios {

    public ArrayList<Usuario> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readUsuariosArray(reader);
        } finally {
            reader.close();
        }
    }

    //crea Array de los ingredientes
    public ArrayList<Usuario> readUsuariosArray(JsonReader reader) throws IOException {

        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

        reader.beginArray();
        while (reader.hasNext()) {
            listaUsuarios.add(readIngrediente(reader));
        }
        reader.endArray();
        return listaUsuarios;
    }

    public Usuario readIngrediente(JsonReader reader) throws IOException {
        String nombreUsuario = null;
        ArrayList<String> listaIngredientes = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String user = reader.nextName();
            if (user.equals("name")) {
                nombreUsuario = reader.nextString();
            } else if (user.equals("ingredientes")) {
                reader.beginArray();
                while (reader.hasNext()) {
                    listaIngredientes.add(reader.nextString());
                }
                reader.endArray();
            }
        }
        reader.endObject();
        return new Usuario(nombreUsuario, listaIngredientes);
    }
}
