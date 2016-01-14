package com.example.nicole.proyectv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.nicole.proyectv1.Despensa.Ingrediente;

/**
 * Created by nicole on 14-01-2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyCookBook.db";
    SQLiteDatabase db;


    //TABLA DE LOS USUARIOS PARA BASE DE DATOS
    private static final String TABLE_NAME   = "usuario";
    private static final String COLUMN_ID    = "id";
    private static final String COLUMN_NAME  = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS  = "pass";

    private static final String TABLE_CREATE = "create table usuario (id integer primary key not null , " +
    "name text not null , email text not null , pass text not null);";

    //TABLA DE INGREDIENTES BASE DE DATOS
    private static final String TABLE_NAME2 = "ingredientes";
    private  static final String COLUMN_ID2 = "id_ingrediente";
    private static final String COLUMN_INGREDIENTE_NAME = "name_ingrediente";

    private static final String TABLE_CREATE2 = "create table ingrediente (id_ingrediente primary key not null , "+
            "name_ingrediente text not null);";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE2);
        this.db = db;

    }

//********************METODOS PARA LA TABLA USUARIO ************************

    public void insertUsuario( Usuario u)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from usuario";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();
        values.put(COLUMN_ID , count);
        values.put(COLUMN_NAME , u.getName());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_PASS, u.getPass());

        db.insert(TABLE_NAME, null, values);

    }

    public String searchPass(String name)
    {
        db = this.getReadableDatabase();
        String query = "select name,pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b ;
        b = "not found";

        if (cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);
                if(a.equals(name))
                {
                    b = cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }

        return b;
    }

//********************METODOS PARA LA TABLA INGREDIENTES************************

    //INSERTAR NUEVO INGREDIENTE
    public void insertIngrediente( Ingrediente u)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from ingrediente";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();
        values.put(COLUMN_ID2 , count);
        //values.put(COLUMN_INGREDIENTE_NAME , u.getName());


        db.insert(TABLE_NAME2, null, values);

    }

    public Cursor getIngredientes ()
    {
        String columnas[] = {COLUMN_ID2, COLUMN_INGREDIENTE_NAME};
        Cursor c = this.getReadableDatabase().query(TABLE_NAME2,columnas,null,null,null,null,null);
        return c;
    }





 //***************PARA ACTUALIZAR LA DB Y VERIFICAR VERSIONES ANTERIORES
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query =  "DROP TABLE IF EXISTS "+TABLE_NAME;
        String query2 = "DROP TABLE IF EXISTS "+TABLE_NAME2;
        db.execSQL(query);
        db.execSQL(query2);
        this.onCreate(db);

    }

//****************PARA CERRAR LA DB*******************************+
    public void close()
    {
        this.close();
    }
}
