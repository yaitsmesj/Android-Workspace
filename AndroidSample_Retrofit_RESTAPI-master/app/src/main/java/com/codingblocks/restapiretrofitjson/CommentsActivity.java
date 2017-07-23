package com.codingblocks.restapiretrofitjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingblocks.restapiretrofitjson.adapters.CommentAdapter;
import com.codingblocks.restapiretrofitjson.api.CommentsAPI;
import com.codingblocks.restapiretrofitjson.api.PostsAPI;
import com.codingblocks.restapiretrofitjson.models.Comment;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        recyclerView = (RecyclerView) findViewById(R.id.rv_comments);
        commentAdapter = new CommentAdapter(this,new ArrayList<Comment>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        PostsAPI.CommentsAPI postsCommentsAPI = retrofit.create(PostsAPI.CommentsAPI.class);
        CommentsAPI commentsAPI = retrofit.create(CommentsAPI.class);

        getIntent().getIntExtra("")


    }
}
