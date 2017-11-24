package com.muelpatmore.week2assignment.data.network.services;

import android.support.constraint.solver.Cache;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.data.constants.API_Constants;

import java.io.File;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Samuel on 24/11/2017.
 */

public class ServerConnection {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static RequestInterface requestInterface;

    public static RequestInterface getServerConnection() {
        retrofit =new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                // Adapter factor required to display data in RecyclerView
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_Constants.BASE_URL)
                .build();
        return retrofit.create(RequestInterface.class);
    }
}

