package com.example.suraj.lecture10json;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Suraj on 6/28/2017.
 */

public class RetrofitObject {

    private Retrofit retrofit;
    private static RetrofitObject retrofitObject=null;
    private RetrofitObject(){

        retrofit =new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static RetrofitObject getInstance(){
        if(retrofitObject==null){
            retrofitObject=new RetrofitObject();
        }
        return retrofitObject;
    }
    public Retrofit getRetrofit(){
        return this.retrofit;
    }


}
