package com.jatinsinghroha.quotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jatinsinghroha.quotesapp.models.Quote;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//Test Comment

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    List<Quote> quotes;

    public  Adapter(List<Quote> quotes) {
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
        holder.quoteAuthor.setText(quotes.get(position).getAuthor());
        holder.quoteContent.setText(quotes.get(position).getContent());

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
}
