package com.magednan.forsale.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.magednan.forsale.R;
import com.magednan.forsale.util.SessionManager;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
            verifyPermissions();
        if(haveConnection()== false){showNetworkDialog();}
        else {goToNextActivity();}
        }
        private void verifyPermissions(){
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA};

            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    permissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    permissions[1]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    permissions[2]) == PackageManager.PERMISSION_GRANTED){
            }else{
                ActivityCompat.requestPermissions(SplashActivity.this,
                        permissions,
                        REQUEST_CODE);
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            verifyPermissions();
        }
        private void goToNextActivity(){
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    sessionManager = new SessionManager(SplashActivity.this);

                    if (sessionManager.isLoggedIn()) {
                        MainActivity.show(SplashActivity.this);
                    } else {
                        AuthActivity.show(SplashActivity.this);
                    }
                }
            }, 1500);

        }


    private boolean haveConnection() {
        boolean haveConnection = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected()){
                    haveConnection = true; }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected()){
                    haveConnection = true; }
        }
        return haveConnection;
    }
    private void showNetworkDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("You are not connected to the net.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Connected",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(haveConnection()){
                            dialog.cancel();
                            goToNextActivity();
                        }else {showNetworkDialog();}
                    }
                });

        builder1.setNegativeButton(
                "Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}