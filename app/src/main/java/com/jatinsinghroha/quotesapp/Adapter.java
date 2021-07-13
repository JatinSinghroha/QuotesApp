package com.jatinsinghroha.quotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jatinsinghroha.quotesapp.models.ProgQuote;

import org.jsoup.Jsoup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    List<ProgQuote> quotes;

    public  Adapter(List<ProgQuote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.quoteAuthor.setText(quotes.get(position).getTitle().getRendered());
        holder.quoteContent.setText(Jsoup.parse(quotes.get(position).getContent().getRendered()).text());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }



    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView quoteAuthor, quoteContent;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            quoteAuthor = itemView.findViewById(R.id.quoteAuthor);
            quoteContent = itemView.findViewById(R.id.quoteContent);
        }
    }
}
