package com.jatinsinghroha.quotesapp.dao;

import com.jatinsinghroha.quotesapp.models.Quote;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOneQuote (Quote quote);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertListOfQuotes (List<Quote> quoteList);

    @Query("SELECT * FROM quotes_table WHERE savedAt > :oneWeekAgoInMillis")
    List<Quote> getQuotesFromLastOneWeek (long oneWeekAgoInMillis);

    @Query("SELECT * FROM quotes_table WHERE id = :id")
    Quote getQuoteByID (String id);

    @Delete
    void deleteOneQuote (Quote quote);

    @Update
    void updateQuote (Quote quote);

    /**
     * select * from quotes_table
     * select id, author, content from quotes_table
     * select * from quotes_table where id = 'xyz'
     * select * from quotes_table where savedAt > 1July
     */
}
