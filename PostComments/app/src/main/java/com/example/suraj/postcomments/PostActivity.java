package com.example.suraj.postcomments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.suraj.postcomments.adapters.PostAdapter;
import com.example.suraj.postcomments.api.PostApi;
import com.example.suraj.postcomments.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "Error";
    private RecyclerView rv_posts;
    private Call<ArrayList<Post>> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final PostAdapter postAdapter = new PostAdapter(new ArrayList<Post>(), this);
        rv_posts = (RecyclerView) findViewById(R.id.rv_posts);
        rv_posts.setLayoutManager(new LinearLayoutManager(this));
        rv_posts.setAdapter(postAdapter);
        postAdapter.setOnPostClickListener(new PostAdapter.OnPostClickListener() {
            @Override
            public void onPostClicked(int postId) {
                Intent i = new Intent(PostActivity.this, CommentsActivity.class);
                i.putExtra("postId", postId);
                startActivity(i);
            }
        });

        PostApi postApi = RetrofitObject.getInstance().getPostApi();

        int userId = getIntent().getIntExtra("userId", 0);
        if (userId == 0) {
            postList = postApi.getPosts();
        } else {
            postList = postApi.getPostsByUserId(userId);
        }

        postList.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Post>> call, @NonNull Response<ArrayList<Post>> response) {
                postAdapter.updatePostList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Post>> call, @NonNull Throwable t) {

                Log.d(TAG, "onFailure: ");
            }
        });

    }
}
