package com.udacity.gradle.builditbigger;

import android.content.Context;
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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import google.louco.com.jokelib.MyClass;

import static com.udacity.gradle.builditbigger.MainActivity.FREE;


/**
 * A placeholder fragment containing a simple view.
 */
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
        new EndpointsAsyncTask().execute(new Pair<Context, GroupObject>(getContext(), new GroupObject(progressBar, "Louco")));
    }

    public void tellJoke() {
        showJoke();
        Log.d("Louco", "The interstitial wasn't loaded yet.");
    }
}
