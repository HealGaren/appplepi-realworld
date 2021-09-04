package com.example.retrofit.view.articlelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.articledetail.ArticlesData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainRecyclerView);
        getServerData();
    }

    private void getServerData() {
        Call<ArticlesData> call = RealWorldClient.getApiService().getArticle();

        call.enqueue(new Callback<ArticlesData>() {
            @Override
            public void onResponse(Call<ArticlesData> call, Response<ArticlesData> response) {
                if (response.isSuccessful()) {
                    ArticlesData articlesData = response.body();
                    ArticleListAdapter adapter = new ArticleListAdapter(articlesData.articles, ArticleListActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("error", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArticlesData> call, Throwable t) {

            }
        });

    }
}