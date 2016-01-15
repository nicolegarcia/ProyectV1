package com.example.nicole.proyectv1.Recetas;

import java.util.ArrayList;

/**
 * Created by SG on 06-01-2016.
 */
public class Receta extends Object{

    String titulo;
    String descripcion;
    ArrayList<String> ingredientes;
    String procedimiento;
    String urlImage;

    public Receta(){

    }
    public Receta(String titulo,
                  String descripcion,
                  ArrayList<String> ingredientes,
                  String procedimiento,
                  String urlImage ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.procedimiento = procedimiento;
        this.urlImage = urlImage;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    public String getUrlImage() {

        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getProcedimiento() {

        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {

        this.procedimiento = procedimiento;
    }


}
