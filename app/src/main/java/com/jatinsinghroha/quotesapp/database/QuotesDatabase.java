package com.jatinsinghroha.quotesapp.database;

import android.content.Context;

import com.jatinsinghroha.quotesapp.dao.QuoteDao;
import com.jatinsinghroha.quotesapp.models.Quote;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Quote.class}, version = 1)
public abstract class QuotesDatabase extends RoomDatabase {

    public abstract QuoteDao quoteDao();

    public static QuotesDatabase INSTANCE;

    public static synchronized QuotesDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuotesDatabase.class, "quotes-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

}
