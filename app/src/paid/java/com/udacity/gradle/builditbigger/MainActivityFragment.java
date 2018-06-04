package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutionException;

import google.louco.com.jokeinterface.JokeActivity;

public class MainActivityFragment extends Fragment {

    private ProgressBar progressBar;
    private Button buttonJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        String productFl = BuildConfig.FLAVOR;
        Log.d("Louco", productFl);
        progressBar = root.findViewById(R.id.progressBar);

        buttonJoke = root.findViewById(R.id.bt_joke);
        buttonJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void showJoke(){
        progressBar.setVisibility(View.VISIBLE);

        EndpointsAsyncTask endpointsAsyncTask =  new EndpointsAsyncTask();
        endpointsAsyncTask.execute(new Pair<Context, GroupObject>(getContext(), new GroupObject(progressBar, "Louco")));
        String result = null;
        try {
            result = endpointsAsyncTask.get();
            if(!result.isEmpty()) {
                Intent intent = new Intent(getContext(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, result);
                getContext().startActivity(intent);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void tellJoke() {
        showJoke();
        Log.d("Louco", "The interstitial wasn't loaded yet.");
    }
}
