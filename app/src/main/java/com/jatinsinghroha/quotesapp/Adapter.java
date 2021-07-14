package com.jatinsinghroha.quotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jatinsinghroha.quotesapp.models.Quote;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    List<Quote> quotes;

    ShareQuote shareQuote;

    public  Adapter(List<Quote> quotes, ShareQuote shareQuote) {
        this.quotes = quotes;
        this.shareQuote = shareQuote;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.quoteAuthor.setText(quote.getAuthor());
        holder.quoteContent.setText(quote.getContent());

        holder.itemView.setOnLongClickListener(v -> {
            shareQuote.share(quote.getContent(), quote.getAuthor());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{
        TextView quoteAuthor, quoteContent;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            quoteAuthor = itemView.findViewById(R.id.quoteAuthor);
            quoteContent = itemView.findViewById(R.id.quoteContent);
        }
    }

    interface ShareQuote {
        void share (String quote, String authorName);
    }
}
