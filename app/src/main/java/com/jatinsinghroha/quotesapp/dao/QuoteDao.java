package com.jatinsinghroha.quotesapp.dao;

import com.jatinsinghroha.quotesapp.models.Quote;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
interface QuoteDao {

    @Insert
    void insertOneQuote (Quote quote);

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
