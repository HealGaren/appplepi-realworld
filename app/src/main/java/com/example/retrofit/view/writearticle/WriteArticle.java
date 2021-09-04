package com.example.retrofit.view.writearticle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.NewArticleData;
import com.example.retrofit.data.SingleArticleResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteArticle extends AppCompatActivity {

    EditText titleEt;
    EditText contentsEt;
    Button publishArticleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_article);

        titleEt = findViewById(R.id.titleEt);
        contentsEt = findViewById(R.id.contentsEt);
        publishArticleBtn = findViewById(R.id.publishArticleBtn);

        publishArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleArticleResponseData singleArticleResponseData = new SingleArticleResponseData();
                String description = singleArticleResponseData.description;
                String title = singleArticleResponseData.title;
                String body = singleArticleResponseData.body;
                List<String> tagList = singleArticleResponseData.tagList;

                Call<SingleArticleResponseData> call = RealWorldClient
                        .getApiService()
                        .postArticle(new NewArticleData(title,description,body,tagList));
                call.enqueue(new Callback<SingleArticleResponseData>() {
                    @Override
                    public void onResponse(Call<SingleArticleResponseData> call, Response<SingleArticleResponseData> response) {
                        if (response.isSuccessful()) {
                            finish();
                        } else {
                            Log.e("error", String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleArticleResponseData> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}