package com.box8.box8home;

import android.app.Application;
import android.net.Uri;
import android.widget.Toast;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class Box8Application extends Application {

    private Picasso picasso;
    private static Box8Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        File file = new File(getCacheDir(), "picasso-cache");
        Cache cache = new Cache(file, 15 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache);

        picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).downloader(new OkHttp3Downloader(builder.build())).build();
    }

    public static Box8Application getInstance() {
        return instance;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
