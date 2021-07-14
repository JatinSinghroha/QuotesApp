package com.jatinsinghroha.quotesapp;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jatinsinghroha.quotesapp.database.QuotesDatabase;
import com.jatinsinghroha.quotesapp.models.ListOfQuotes;
import com.jatinsinghroha.quotesapp.models.Quote;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements Adapter.ShareQuote {
    RecyclerView recyclerView;
    Adapter adapter;
    ListOfQuotes listOfQuotes;
    QuotesDatabase quotesDatabase;
    LottieAnimationView noInternetAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupAnim();

        quotesDatabase = QuotesDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        fetchQuotes();

        //get1000QuotesFromVolley - Done
        //insert1000Quotes - 500 were inserted
        //get1000QuotesFromDatabase - 500
        //Display in Adapter

        //insert1000Quotes : Completable, Single<Int>, Single<Quote>, Single<List<Quote>>
        //insert1000Quotes.subscribe {}
        //insert1000Quotes.andThen
    }

    private void setupAnim() {
        noInternetAnim = findViewById(R.id.noInternetAnim);

        noInternetAnim.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                fetchQuotes();
            }
        });
    }

    private void fetchQuotes() {

        if (isNetworkConnected()) {
            noInternetAnim.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            RequestQueue queue = Volley.newRequestQueue(this);
            String JSON_URL = "https://quotable.io/quotes?page="+ (new Random().nextInt(96));
            JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                    response -> {
                        Log.i("tag", JSON_URL);
                        listOfQuotes = new Gson().fromJson(response.toString(), ListOfQuotes.class);

                        quotesDatabase.quoteDao().insertListOfQuotes(listOfQuotes.getQuotes());

                        setupRecyclerView(getQuotes());

                    }, error -> Log.e("tag", "onErrorResponse: " + error.getMessage()));

            queue.add(jsonArrayRequest);
        } else {
                List<Quote> listOfQuotes = getQuotes();

                if (listOfQuotes.isEmpty()) {
                    displayAnimation();
                } else {
                    setupRecyclerView(listOfQuotes);
                }
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void displayAnimation() {
        recyclerView.setVisibility(View.GONE);
        noInternetAnim.setVisibility(View.VISIBLE);
    }

    private void setupRecyclerView(List<Quote> listOfQuotes) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new Adapter(listOfQuotes, this);

        recyclerView.setAdapter(adapter);
    }

    private List<Quote> getQuotes() {
        return quotesDatabase.quoteDao()
                .getAllSavedQuotes();
    }

    @Override
    public void share(String quote, String authorName) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, quote + "\n\n- "+authorName);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Share this Quote");
        startActivity(shareIntent);
    }
}
