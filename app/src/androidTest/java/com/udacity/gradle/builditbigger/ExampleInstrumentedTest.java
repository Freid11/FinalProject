package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useBuilder() {
        // Context of the app under test.
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.3.3:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        MyApi myApi = builder.build();
        String startJoke = "Hi, ";
        String Name = "LOUCO";
        try {
            String joke = myApi.sayHi(Name).execute().getData();
            assertEquals(joke ,startJoke+Name);
        } catch (IOException e) {
            assertEquals(e ,startJoke+Name);
            e.printStackTrace();
        }

    }
}
