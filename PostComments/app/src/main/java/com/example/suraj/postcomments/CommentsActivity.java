package com.example.suraj.postcomments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.suraj.postcomments.adapters.CommentAdapter;
import com.example.suraj.postcomments.api.PostApi;
import com.example.suraj.postcomments.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "Error";
    private RecyclerView rv_comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        rv_comments = (RecyclerView) findViewById(R.id.rv_comments);
        final CommentAdapter commentAdapter = new CommentAdapter(new ArrayList<Comment>(), this);
        rv_comments.setLayoutManager(new LinearLayoutManager(this));
        rv_comments.setAdapter(commentAdapter);

        PostApi postApi = RetrofitObject.getInstance().getPostApi();

        int postId = getIntent().getIntExtra("postId", 0);

        postApi.getComments(postId).enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Comment>> call, @NonNull Response<ArrayList<Comment>> response) {
                commentAdapter.updateComments(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Comment>> call, @NonNull Throwable t) {

                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
