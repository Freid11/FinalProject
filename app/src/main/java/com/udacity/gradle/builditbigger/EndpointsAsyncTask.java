package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.sql.Time;

import google.louco.com.jokeinterface.JokeActivity;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, GroupObject>, Void, String> {
    public static final String HTTPath = " http://10.0.3.3:8080/_ah/api/";
    private MyApi myApiService = null;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private ProgressBar progressBar;

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, GroupObject>... params) {
        context = params[0].first;
        String name = params[0].second.getName();
        progressBar = params[0].second.getProgressBar();

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(HTTPath)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if(progressBar != null)
            progressBar.setVisibility(View.GONE);

    }

}

