package com.example.suraj.postcomments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suraj.postcomments.R;
import com.example.suraj.postcomments.models.Comment;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/29/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentsViewHolder> {

    private final Context context;
    private ArrayList<Comment> commentsList;

    public CommentAdapter(ArrayList<Comment> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }

    public void updateComments(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
        notifyDataSetChanged();
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.users_list, parent, false);

        return new CommentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {

        Comment comment = commentsList.get(position);
        holder.tv_name.setText(comment.getName());
        holder.tv_body.setText(comment.getBody());
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_name;
        final TextView tv_body;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_UserList_Name);
            tv_body = (TextView) itemView.findViewById(R.id.tv_UserList_Username);
        }
    }
}
