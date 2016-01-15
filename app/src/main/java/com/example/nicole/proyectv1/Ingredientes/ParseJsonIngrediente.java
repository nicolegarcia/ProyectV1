package com.example.nicole.proyectv1.Ingredientes;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by nicole on 15-01-2016.
 */
public class ParseJsonIngrediente {

    public ArrayList<Ingrediente> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readIngredientesArray(reader);
        } finally {
            reader.close();
        }
    }

    //crea Array de los ingredientes
    public ArrayList<Ingrediente> readIngredientesArray(JsonReader reader) throws IOException {

        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        reader.beginArray();
        while (reader.hasNext()) {
            ingredientes.add(readIngrediente(reader));
        }
        reader.endArray();
        return ingredientes;
    }



    public Ingrediente readIngrediente(JsonReader reader) throws IOException {
        String nombre_ingrediente = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String ingred = reader.nextName();
            if (ingred.equals("ingrediente")) {
                nombre_ingrediente = reader.nextString();
            }
        }
        reader.endObject();
        return new Ingrediente(nombre_ingrediente);
    }
}
