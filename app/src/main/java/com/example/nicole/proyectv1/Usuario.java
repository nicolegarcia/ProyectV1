package com.example.nicole.proyectv1;

public class Usuario {

    int id;
    String name, email, pass;
    // PARA EL ID
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return this.id;
    }

    //PARA EL NOMBRE DE USUARIO

    public void setName( String name)
    {
        this.name = name;
    }
    public String getName()
    {
         return this.name;
    }

    // PARA EL CORREO

    public void setEmail( String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }

    // PARA LA PASSWORD

    public void setPass( String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }


}
