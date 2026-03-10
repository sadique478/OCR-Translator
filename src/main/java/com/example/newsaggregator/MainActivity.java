package com.example.newsaggregator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView news ;
    private NewsAdapter nadapter;
    private ArrayList<NewsItem>mExampleList;
    private RequestQueue nRq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news = findViewById(R.id.Newslist);
        news.setHasFixedSize(true);
        news.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        nRq = Volley.newRequestQueue(this);
        pareJSON();

    }
    private void pareJSON(){
        String url = "https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=81c034f00ef04d8485616705ea1cb747";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   JSONArray jsonArray = response.getJSONArray("articles");


                    for(int i = 0 ; i < jsonArray.length() ; i++){
                        JSONObject article = jsonArray.getJSONObject(i);

                       String title = article.getString("description");

                        mExampleList.add(new NewsItem(title));


                   }
                    nadapter = new NewsAdapter(MainActivity.this,mExampleList);
                    news.setAdapter(nadapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        nRq.add(request);
    }
}
