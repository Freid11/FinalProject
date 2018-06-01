package com.udacity.gradle.builditbigger;

import android.widget.ProgressBar;

public class GroupObject {
    private ProgressBar progressBar;
    private String Name;

    public GroupObject(ProgressBar progressBar, String name) {
        this.progressBar = progressBar;
        Name = name;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public String getName() {
        return Name;
    }
}
