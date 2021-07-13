package com.jatinsinghroha.quotesapp;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jatinsinghroha.quotesapp.models.ProgQuote;

import java.lang.reflect.Type;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    QuotesDatabase quotesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void fetchQuotes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String JSON_URL = "https://quotesondesign.com/wp-json/wp/v2/posts/?orderby=rand";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
                response -> {
//            ListOfQuotes listOfQuotes = new Gson().fromJson(response.toString(), ListOfQuotes.class);

                    Log.e("ABCD", response.toString());
                    Gson gson = new Gson();

                    Type listType = new TypeToken<List<ProgQuote>>() {}.getType();
                    List<ProgQuote> listOfQuotes = gson.fromJson(response.toString(), listType);
                    quotesDatabase.progQuoteDao().insertListOfQuotes(listOfQuotes);
//            quotesDatabase.quoteDao().insertListOfQuotes(listOfQuotes.getQuotes());
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new Adapter(quotesDatabase.progQuoteDao().getQuotes());

            recyclerView.setAdapter(adapter);
        }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage()));

        queue.add(jsonArrayRequest);

    }
}
