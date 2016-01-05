package com.example.nicole.proyectv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by SG on 05-01-2016.
 */
public class HomeActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        irDespensa();
    }

    private void irDespensa(){
        Button despensa = (Button) findViewById(R.id.btn_VerDespensa);
        despensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DespensaActivity.class));

            }
        });
    }
}