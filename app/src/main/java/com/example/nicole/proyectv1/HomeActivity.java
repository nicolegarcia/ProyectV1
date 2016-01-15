package com.example.nicole.proyectv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicole.proyectv1.Recetas.RecetasActivity;
import com.facebook.Profile;

/**
 * Created by SG on 05-01-2016.
 */

public class HomeActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        TextView name = (TextView) findViewById(R.id.userName);
        Profile profile = Profile.getCurrentProfile();
        name.setText("BIENVENID@ \n".concat(profile.getName()));

        irDespensa();
        irRecetas();
    }

    private void irDespensa(){
        Button despensa = (Button) findViewById(R.id.btn_VerDespensa);
        despensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Display_Login.class));

            }
        });
    }

    private void irRecetas(){
        Button recetas = (Button) findViewById(R.id.btn_BuscarReceta);
        recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RecetasActivity.class));

            }
        });
    }
}
