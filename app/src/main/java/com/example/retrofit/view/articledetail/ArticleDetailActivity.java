package com.example.retrofit.view.articledetail;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.articledetail.ArticleDetailCommentData;
import com.example.retrofit.data.articledetail.ArticleDetailData;
import com.example.retrofit.data.articledetail.Comment;
import com.example.retrofit.databinding.ActivityArticleDetailBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailActivity extends AppCompatActivity {

    private ActivityArticleDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);

        Intent i = getIntent();
        String articleSlug = i.getStringExtra("articleSlug");
        getArticleData(articleSlug);
        getCommentData(articleSlug);
    }

    private void getCommentData(String articleSlug) {
        Call<ArticleDetailCommentData> call = RealWorldClient.getApiService().getArticleComment(articleSlug);

        call.enqueue(new Callback<ArticleDetailCommentData>() {
            @Override
            public void onResponse(Call<ArticleDetailCommentData> call, Response<ArticleDetailCommentData> response) {
                if(response.isSuccessful()){
                    ArticleDetailCommentData data = response.body();
                    assert data != null;
                    ArrayList<Comment> comments = (ArrayList<Comment>) data.getComments();


                }
                else{
                    Toast.makeText(ArticleDetailActivity.this, "댓글 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailCommentData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getArticleData(String articleSlug) {
        Call<ArticleDetailData> call = RealWorldClient.getApiService().getArticleDetail(articleSlug);

        call.enqueue(new Callback<ArticleDetailData>() {
            @Override
            public void onResponse(Call<ArticleDetailData> call, Response<ArticleDetailData> response) {
                if(response.isSuccessful()){

                    ArticleDetailData result = response.body();
                    binding.articlePostText.setText(result.articleDetailPost.description);
                    binding.titleText.setText(result.articleDetailPost.title);
                    Glide.with(ArticleDetailActivity.this)
                            .load(result.author.image)
                            .into(binding.writerImageView);
                }
                else{
                    Log.e(TAG, "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}