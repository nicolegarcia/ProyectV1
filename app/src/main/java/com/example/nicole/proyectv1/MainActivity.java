package com.example.nicole.proyectv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                try {
                    crearUsuario();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "no creó usuario", Toast.LENGTH_SHORT);
                }
                System.out.print("Logged in");
            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(MainActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
                Log.i("Error", "Error");
            }
        });

        //printHashKey();

    }

    public void onButtonClick(View v){

        if (v.getId() == R.id.display_button)
        {
            Intent i = new Intent(MainActivity.this, Display_Login.class);
            startActivity(i);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

/*    public void printHashKey(){ ------------------>//Key para funcionar facebook
        TextView algo = (TextView) findViewById(R.id.key);

        try{
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.nicole.proyectv1",
                    PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                algo.setText(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e){


        } catch (NoSuchAlgorithmException e){

        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    void crearUsuario() throws JSONException {
        String name = Profile.getCurrentProfile().getName();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        arr.put("harina");
        arr.put("papas");
        arr.put("huevo");
        arr.put("cebolla");

        obj.put("name", name);
        obj.put("ingredient", arr);

        try{
            FileOutputStream fileout = new FileOutputStream("/res/raw/usuarios.json");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(obj.toString());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "no se agregó ingrediente", Toast.LENGTH_SHORT).show();
        }
    }

}
