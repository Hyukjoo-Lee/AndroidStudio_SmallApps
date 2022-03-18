package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textview = findViewById(R.id.textView1);
    }

    public void startTask(View view) {
        SimpleAsyncTask task = new SimpleAsyncTask(this);
        // It freezes our background task for 10 seconds
        task.execute(10);
    }

    // Integer : how long time it should run
    private static class SimpleAsyncTask extends AsyncTask<Integer, Integer, String> {
        private WeakReference<MainActivity> activityWeakReference;

        public SimpleAsyncTask(MainActivity mainActivity) {
            activityWeakReference = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.progressBar.setScaleY(3f); // weight y-axis
            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);

            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 100;

            // Sleep for the random amount of time
            for (int i = 0; i < integers[0]; i++) {
                publishProgress((i * 100) / integers[0]);
                try {
                    Thread.sleep(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.textview.setText("Napping...");
            activity.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            activity.textview.setText("Finished");
            activity.progressBar.setProgress(0);
            activity.progressBar.setVisibility(View.VISIBLE);
        }
    }
}
