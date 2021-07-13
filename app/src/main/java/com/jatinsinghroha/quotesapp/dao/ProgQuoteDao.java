package com.jatinsinghroha.quotesapp.dao;

import com.jatinsinghroha.quotesapp.models.ProgQuote;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProgQuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOneQuote (ProgQuote progQuote);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertListOfQuotes (List<ProgQuote> progQuoteList);

    @Query("SELECT * FROM programming_quotes_table WHERE savedAt > :oneWeekAgoInMillis")
    List<ProgQuote> getQuotesFromLastOneWeek (long oneWeekAgoInMillis);

    @Query("SELECT * FROM programming_quotes_table WHERE id = :id")
    ProgQuote getQuoteByID (String id);

    @Query("SELECT * FROM programming_quotes_table")
    List<ProgQuote> getQuotes();

    @Delete
    void deleteOneQuote (ProgQuote progQuote);

    @Update
    void updateQuote (ProgQuote progQuote);

    /**
     * select * from quotes_table
     * select id, author, content from quotes_table
     * select * from quotes_table where id = 'xyz'
     * select * from quotes_table where savedAt > 1July
     */
}
