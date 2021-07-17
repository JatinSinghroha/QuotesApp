package com.jatinsinghroha.quotesapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import es.dmoral.toasty.Toasty;

public class AskPermissionsActivity extends AppCompatActivity{

    private static final int REQUEST_AUDIO = 1, REQUEST_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_permissions);


    }

    /**
     *
     * 1. Permission not there. Ask it.
     * 2. Permission is there. Show Toast Message. -- DONE
     * 3. We cannot do anything. -- DONE
     *
     * 1A. You ask for Permission but user deny.
     * 1B. We get the permission when asked.
     */

    public void onClickListener(View view) {
        switch (view.getId()) {
            default:
            case R.id.micPermission: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                        == PackageManager.PERMISSION_GRANTED) {

                    Toasty.success(this, "Already have Permission", Toasty.LENGTH_LONG, true).show();

                } else {
                    Toasty.info(this, "App requires MIC Permission", Toasty.LENGTH_LONG, true).show();

                    requestPermission(Manifest.permission.RECORD_AUDIO, REQUEST_AUDIO);
                }
                break;
            }

            case R.id.cameraPermission: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {

                    Toasty.success(this, "Already have Permission", Toasty.LENGTH_LONG, true).show();

                } else {
                    Toasty.info(this, "App requires Camera Permission", Toasty.LENGTH_LONG, true).show();

                    requestPermission(Manifest.permission.CAMERA, REQUEST_CAMERA);
                }
                break;
            }
        }
    }

    private void requestPermission(String permissionName, int requestCode) {

        String[] permissionArray = {permissionName};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, permissionArray, requestCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            default:
            case REQUEST_AUDIO: {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toasty.success(this, "Thank you for giving MIC permission", Toasty.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(this, "Permission Denied. Can't use Mic feature", Toasty.LENGTH_LONG, true).show();
                }
                break;
            }
            case REQUEST_CAMERA: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toasty.success(this, "Thank you for giving Camera permission", Toasty.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(this, "Permission Denied. Can't use Camera feature", Toasty.LENGTH_LONG, true).show();
                }
                break;
            }
        }

    }
}