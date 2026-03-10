package com.example.newsaggregator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context econtext;
    private ArrayList<NewsItem> mExampleList;

    public NewsAdapter(Context context, ArrayList<NewsItem> exampleList){
        econtext = context;
        mExampleList= exampleList;

    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
       View v = LayoutInflater.from(econtext).inflate(R.layout.list_news_list,parent,false);

        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        NewsItem currentItem = mExampleList.get(position);
        String title  = currentItem.getTitle();


        newsViewHolder.txtt.setText(title);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public  class NewsViewHolder extends  RecyclerView.ViewHolder{

       public TextView txtt;
        public  NewsViewHolder(View itemView){
            super(itemView);

            txtt = itemView.findViewById(R.id.tx);

        }
    }
}
