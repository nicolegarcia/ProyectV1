package com.example.nicole.proyectv1.Recetas;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by SG on 15-01-2016.
 */

//Clase para adquirir recetas desde Json

public class ParseJsonReceta {

    //recibe parámetro del tipo InputStream que contiene recurso json
    //llama a readRecetasArray para crear un ArrayList del tipo Receta
    public ArrayList<Receta> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readRecetasArray(reader);
        } finally {
            reader.close();
        }
    }
        //readRecetaArray recibe receta desde readReceta para crear Array de recetas
    public ArrayList<Receta> readRecetasArray(JsonReader reader) throws IOException {

        ArrayList<Receta> recetas = new ArrayList<Receta>();

        reader.beginArray();
        while (reader.hasNext()) {
            recetas.add(readReceta(reader));
        }
        reader.endArray();
        return recetas;
    }
        //readReceta recibe Json, retorna receta
    public Receta readReceta(JsonReader reader) throws IOException {
        String nombre = null;
        String url = null;
        String descripcion = null;
        ArrayList<String> ingredientes = new ArrayList<String>();
        String procedimiento = null;

        //Inicia objeto receta recibido
        reader.beginObject();
        while (reader.hasNext()) {
            String receta = reader.nextName();
            if (receta.equals("name")) { //guarda String nombre
                nombre = reader.nextString();
            } else if (receta.equals("imagen")) { //guarda String url imgen
                url = reader.nextString();
            } else if (receta.equals("description")) { //guarda String descripcion
                descripcion = reader.nextString();
            } else if (receta.equals("ingredientes")) { //Inicia Array para leer ingredientes
                reader.beginArray();
                while (reader.hasNext()) {
                    ingredientes.add(reader.nextString());
                }
                reader.endArray();
            } else if (receta.equals("procedimiento")) { //guarda String procedimiento
            procedimiento = reader.nextString();
            }
        }
        reader.endObject();
        return new Receta(nombre, descripcion,ingredientes,procedimiento, url );
    }
}

