package com.jatinsinghroha.quotesapp;

import android.content.Context;

import com.jatinsinghroha.quotesapp.dao.ProgQuoteDao;
import com.jatinsinghroha.quotesapp.dao.QuoteDao;
import com.jatinsinghroha.quotesapp.models.ProgQuote;
import com.jatinsinghroha.quotesapp.models.Quote;
import com.jatinsinghroha.quotesapp.models.type_converters.ContentTypeConverter;
import com.jatinsinghroha.quotesapp.models.type_converters.TitleTypeConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Quote.class, ProgQuote.class}, version = 2)
@TypeConverters({TitleTypeConverter.class, ContentTypeConverter.class})
public abstract class QuotesDatabase extends RoomDatabase {

    public static QuotesDatabase INSTANCE;

    public abstract QuoteDao quoteDao();
    public abstract ProgQuoteDao progQuoteDao();

    public static synchronized QuotesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuotesDatabase.class, "quotes-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
