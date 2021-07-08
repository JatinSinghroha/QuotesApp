package com.jatinsinghroha.quotesapp;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jatinsinghroha.quotesapp.models.ListOfQuotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private static String JSON_URL = "https://quotable.io/quotes?page=1";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    private void fetchQuotes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                response -> {
            ListOfQuotes listOfQuotes = new Gson().fromJson(response.toString(), ListOfQuotes.class);

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new Adapter(listOfQuotes.getQuotes());
            recyclerView.setAdapter(adapter);
        }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage()));

        queue.add(jsonArrayRequest);

    }
}
