package com.example.suraj.postcomments.api;

import com.example.suraj.postcomments.models.Comment;
import com.example.suraj.postcomments.models.Post;
import com.example.suraj.postcomments.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Suraj on 6/28/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface PostApi {


    @GET("/posts")
    Call<ArrayList<Post>> getPostsByUserId(
            @Query("userId") int userId
    );

    @GET("/posts")
    Call<ArrayList<Post>> getPosts();

    @GET("/users")
    Call<ArrayList<User>> getUsers();


    @GET("/comments")
    Call<ArrayList<Comment>> getComments(
            @Query("postId") int postId
    );

}
