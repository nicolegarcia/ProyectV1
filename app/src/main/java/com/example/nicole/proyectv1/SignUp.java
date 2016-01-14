package com.example.nicole.proyectv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by nicole on 13-01-2016.
 */
public class SignUp extends Activity {

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }

    public void onSignUpClick(View v) {


        if (v.getId() == R.id.Bsignupbutton) {

            EditText name = (EditText) findViewById(R.id.TFusername);
            EditText email = (EditText) findViewById(R.id.TFemail);
            EditText pass1 = (EditText) findViewById(R.id.TFpass1);
            EditText pass2 = (EditText) findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();


            if (!pass1str.equals(pass2str)) {
                Toast pass = Toast.makeText(SignUp.this, "Passwords dont match!", Toast.LENGTH_LONG);
                pass.show();
            }
            //se insertaran datos a la base de datos y se redirigira al HOME
            else {
                Usuario u = new Usuario();
                u.setName(namestr);
                u.setEmail(emailstr);
                u.setPass(pass1str);

                helper.insertUsuario(u);
                Toast pass = Toast.makeText(SignUp.this, "usuario correcto creado!", Toast.LENGTH_LONG);
                pass.show();

                Intent j = new Intent(SignUp.this, Display_Login.class);
                startActivity(j);
            }


        }
    }
}
