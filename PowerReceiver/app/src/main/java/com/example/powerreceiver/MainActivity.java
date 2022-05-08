package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.powerreceiver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CustomReceiver receiver = new CustomReceiver();

    // String constant that defines the custom broadcast Action.
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        this.registerReceiver(receiver, filter);

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        // Generate a random integer between 1 - 20
        int ranNum = (int)(Math.random() * 20) + 1;
        // Convert to String and set the number
        binding.txtRandomNum.setText(String.valueOf(ranNum));
        // Add the number to the 'extra' the field of the intent
        customBroadcastIntent.putExtra("random number", ranNum);
        // Sends the ranNum as an Intent extra in the LocalBroadcast
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

}