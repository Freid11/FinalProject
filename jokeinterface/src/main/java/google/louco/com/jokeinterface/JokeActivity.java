package google.louco.com.jokeinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import google.louco.com.jokelib.MyClass;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "JOKE_KEY";
    private TextView jokeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeText = (TextView) findViewById(R.id.tv_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_KEY);

        jokeText.setText(joke);
    }
}
