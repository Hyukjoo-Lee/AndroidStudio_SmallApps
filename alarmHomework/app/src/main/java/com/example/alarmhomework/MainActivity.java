package com.example.alarmhomework;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alarmhomework.databinding.ActivityMainBinding;

import java.util.Calendar;

/**
 * This is the simple alarm app that notify you to wake up at a specific time.
 * 'Alarm Manager' is a component that makes that possible.
 *
 * <receiver android:name=".AlarmReceiver"/> should be declared in Manifest.xml
 *
 * When you set the time in time_picker and press the set button, Notification occurs at that time
 * AlarmManager.INTERVAL_DAY is used so that an alarm will occur every day
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // RequiresApi to get time from TimePicker
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSetTime.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int hour = binding.timePicker.getHour();
            int minute = binding.timePicker.getMinute();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }

            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                Intent notifyIntent = new Intent(this, AlarmReceiver.class);

                //  Set PendingIntent.. we can get the intend by using requestCode = '1'
                PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 1, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);

                Toast.makeText(MainActivity.this, "Alarm is set.", Toast.LENGTH_LONG).show();
            }
        });
    }
}