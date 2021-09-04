package com.example.retrofit.view.articlelist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.data.ArticleData;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    List<ArticleData> articleDataList;
    Context context;

    public ArticleListAdapter(List<ArticleData> articleDataList, Context context) {
        super();
        this.articleDataList = articleDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_article, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListAdapter.ViewHolder holder, int position) {

        String username = articleDataList.get(position).author.username;
        String title = articleDataList.get(position).title;
        String createdAt = articleDataList.get(position).createdAt;
        String description = articleDataList.get(position).description;
        List<String> tagList = articleDataList.get(position).tagList;
        int likeCount = articleDataList.get(position).favoritesCount;
        StringBuilder tag = new StringBuilder();

        for (int i = 0; i < tagList.size(); i++) {
            tag.append("#").append(tagList.get(i));
        }

        Glide.with(context)
                .load(articleDataList.get(position).author.image)
                .into(holder.profileImage);
        holder.usernameTv.setText(username);
        holder.titleTv.setText(title);
        holder.createdAtTv.setText(createdAt);
        holder.descriptionTv.setText(description);
        holder.tagTv.setText(tag);
        holder.likeCountTv.setText(String.valueOf(likeCount));
    }

    @Override
    public int getItemCount() {
        return articleDataList != null ? articleDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView usernameTv;
        TextView titleTv;
        TextView createdAtTv;
        TextView descriptionTv;
        TextView tagTv;
        TextView likeCountTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            usernameTv = itemView.findViewById(R.id.usernameTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            createdAtTv = itemView.findViewById(R.id.createdAtTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            tagTv = itemView.findViewById(R.id.tagTv);
            likeCountTv = itemView.findViewById(R.id.likeCountTv);

            itemView.setOnClickListener(view -> {
                ArticleData article = articleDataList.get(this.getAdapterPosition());

                Context context = view.getContext();
                context.startActivity(
                        new Intent(context, null) // TODO: 액티비티 개발되면 class 연결
                                .putExtra("articleSlug", article.slug)
                );
            });
        }
    }
}
