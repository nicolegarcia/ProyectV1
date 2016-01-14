package com.example.nicole.proyectv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by nicole on 13-01-2016.
 */
public class Display_Login extends Activity {

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_login);

    }


    public void onButtonLoginClick(View vl) {

        if (vl.getId() == R.id.loginlocal_button)
        {
            EditText a = (EditText)findViewById(R.id.TVusername);
            String str = a.getText().toString();

            EditText b = (EditText)findViewById(R.id.TVpass);
            String pass = a.getText().toString();

            String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                Intent i = new Intent(Display_Login.this, HomeActivity.class);
                startActivity(i);
            }
            else
            {
                Toast temp2 = Toast.makeText(Display_Login.this, "Usuario y contraseña incorrectos", Toast.LENGTH_LONG);
                temp2.show();
                Toast temp = Toast.makeText(Display_Login.this, "Si no estas resgistrado, Hazlo ahora!", Toast.LENGTH_LONG);
                temp.show();

            }

        }
        if (vl.getId() == R.id.Signuplocal_button) {
            Intent j = new Intent(Display_Login.this, SignUp.class);

            startActivity(j);

        }
    }

}