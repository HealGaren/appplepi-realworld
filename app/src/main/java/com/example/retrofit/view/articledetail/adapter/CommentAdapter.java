package com.example.retrofit.view.articledetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.data.articledetail.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{
    ArrayList<Comment> comments;
    public CommentAdapter(ArrayList<Comment> comments) {
        super();
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_comment_layout, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.commentBio.setText(comments.get(position).getBody());
        Glide.with(holder.itemView)
                .load(comments.get(position).getAuthor().getImage())
                .into(holder.commentWriterImage);
        holder.commentWriterText.setText(comments.get(position).getAuthor().getUsername());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView commentWriterText;
        ImageView commentWriterImage;
        TextView commentBio;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentWriterText = itemView.findViewById(R.id.commentWriterText);
            commentBio = itemView.findViewById(R.id.commentBio);
            commentWriterImage = itemView.findViewById(R.id.commentWriterImage);
        }
    }
}
