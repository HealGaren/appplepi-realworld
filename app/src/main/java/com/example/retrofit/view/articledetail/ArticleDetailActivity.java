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
import com.example.retrofit.data.articledetail.commentdata.CommentData;
import com.example.retrofit.databinding.ActivityArticleDetailBinding;
import com.example.retrofit.view.articledetail.adapter.CommentAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailActivity extends AppCompatActivity {

    private ActivityArticleDetailBinding binding;
    private ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);

        Intent i = getIntent();
        String articleSlug = i.getStringExtra("articleSlug");
        getArticleData(articleSlug);
        getCommentData(articleSlug);


        binding.button.setOnClickListener(view -> {
            if(binding.commentEditText.getText().toString().isEmpty()){
                Toast.makeText(ArticleDetailActivity.this, "댓글을 입력해주세요", Toast.LENGTH_SHORT).show();
            }
            else{
                postCommentData(articleSlug);
            }
        });

    }

    private void postCommentData(String articleSlug) {
        Call<CommentData> call = RealWorldClient.getApiService().postArticleComment(articleSlug, binding.commentEditText.getText().toString());

        call.enqueue(new Callback<CommentData>() {
            @Override
            public void onResponse(Call<CommentData> call, Response<CommentData> response) {
                if(response.isSuccessful()){
                    getCommentData(articleSlug);
                }
                else{
                    Toast.makeText(ArticleDetailActivity.this, "댓글 작성하기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommentData> call, Throwable t) {

            }
        });
    }

    private void getCommentData(String articleSlug) {
        Call<ArticleDetailCommentData> call = RealWorldClient.getApiService().getArticleComment(articleSlug);

        call.enqueue(new Callback<ArticleDetailCommentData>() {
            @Override
            public void onResponse(Call<ArticleDetailCommentData> call, Response<ArticleDetailCommentData> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: 성공한 이유"+response);
                    ArticleDetailCommentData data = response.body();
                    assert data != null;
                    comments = (ArrayList<Comment>) data.getComments();
                    binding.commentRecycler.setAdapter(new CommentAdapter(comments));
                }
                else{
                    Toast.makeText(ArticleDetailActivity.this, "댓글 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: 실패한 이유"+response);
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

                    Log.d(TAG, "onResponse: call "+response);
                    ArticleDetailData result = response.body();
                    Log.d(TAG, "onResponse: result : "+result);
                    binding.articlePostText.setText(result.article.description);
                    binding.titleText.setText(result.article.title);
                    Glide.with(ArticleDetailActivity.this)
                            .load(result.article.author.image)
                            .into(binding.writerImageView);
                    binding.writerText.setText(result.article.author.username);
                }
                else{
                    Log.e(TAG, "onResponse: ");
                    Log.d(TAG, "onResponse: response : "+response);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}