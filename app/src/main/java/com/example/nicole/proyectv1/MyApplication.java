package com.example.nicole.proyectv1;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nicole on 14-01-2016.
 */
public class MyApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            printhashkey();

        }

        public void printhashkey(){

            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.example.nicole.proyectofinal",
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("NICOLE:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
        }

}




