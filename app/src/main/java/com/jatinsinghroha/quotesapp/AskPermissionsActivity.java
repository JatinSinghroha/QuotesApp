package com.jatinsinghroha.quotesapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

                ((Button) findViewById(view.getId())).setText(this.getResources().getQuantityString(R.plurals.winner_text, 1, 1));

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
                ((Button) findViewById(view.getId())).setText(this.getResources().getQuantityString(R.plurals.winner_text, 10, 10));

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
                    ((Button) findViewById(R.id.micPermission)).setText(this.getResources().getString(R.string.permission_given_text));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.home_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_item:{
                Toasty.success(this, "Thank you for Searching.", Toasty.LENGTH_LONG, true).show();
                break;
            }

            case R.id.open_website_item: {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://technologyend.com")));
                break;
            }

            case R.id.email_me_item:{
                Intent emailMe = new Intent(Intent.ACTION_SENDTO);

                emailMe.setData(Uri.parse("mailto:"));

                emailMe.putExtra(Intent.EXTRA_EMAIL, "my895012@gmail.com");

                emailMe.putExtra(Intent.EXTRA_SUBJECT, "QuotesApp v1.0 Contact");

                emailMe.putExtra(Intent.EXTRA_TEXT, "App Name - QuotesApp\nContact From - Overflow Menu");

                if (emailMe.resolveActivity(this.getPackageManager()) != null)
                    startActivity(emailMe);

                break;
            }

            case R.id.contact_item: {
                Toasty.success(this, "Save My Contact", Toasty.LENGTH_SHORT, true).show();

                Intent saveMyContact = new Intent(Intent.ACTION_INSERT);

                saveMyContact.setType(ContactsContract.Contacts.CONTENT_TYPE);

                saveMyContact.putExtra(ContactsContract.Intents.Insert.NAME, "Jatin Singhroha");

                saveMyContact.putExtra(ContactsContract.Intents.Insert.PHONE, "+91 8950121519");

                saveMyContact.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, "+91 7015285543");

                saveMyContact.putExtra(ContactsContract.Intents.Insert.EMAIL, "my895012@gmail.com");

                saveMyContact.putExtra(ContactsContract.Intents.Insert.SECONDARY_EMAIL, "jatin@technologyend.com");

                saveMyContact.putExtra(ContactsContract.Intents.Insert.COMPANY, "SURE Trust");

                saveMyContact.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, "Android Developer");

                startActivity(saveMyContact);

                break;
            }

            case R.id.settings_item:{
                Toasty.info(this, "Thank you", Toasty.LENGTH_LONG, true).show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}