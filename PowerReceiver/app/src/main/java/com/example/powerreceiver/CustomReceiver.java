package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    // String constant that defines the custom broadcast Action.
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        int intentNum = intent.getIntExtra("random number", 0);

            if (intentAction != null) {
                String toastMessage = "unknown intent action";

                switch (intentAction) {
                    case Intent.ACTION_POWER_CONNECTED:
                        toastMessage = "Power Connected";
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED:
                        toastMessage = "Power Disconnected";
                        break;
                    case ACTION_CUSTOM_BROADCAST:
                        toastMessage = "Custom Broadcast Received\n"
                                + "Square of the Random number: " + (intentNum * intentNum);
                        break;
                }
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            }

    }
}