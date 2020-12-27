package com.example.alarmclock;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
TimePicker timePicker;
ToggleButton toggleButton;
Boolean isClicked=true;
AlarmManager ringtoneManager;
PendingIntent pendingIntent;
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    timePicker=findViewById(R.id.timePicker);
    toggleButton=findViewById(R.id.toogleButton);
    ringtoneManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    toggleButton.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            clicked();
        }
    });











    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void clicked() {

        if (toggleButton.isChecked())
        {

            Toast.makeText(this, "Alarm is ON ", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,AlarmReceiver.class);
            pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
            calendar.set(Calendar.MINUTE,timePicker.getMinute());
            long time=calendar.getTimeInMillis()-(calendar.getTimeInMillis());
            if (System.currentTimeMillis() > time)
            {
                if (calendar.AM_PM ==0)
                {
                    time=time + (1000 * 60 * 60 * 12);
                }
                else {
                    time=time + (1000 * 60 * 60 * 24);

                }

            }
            ringtoneManager.setRepeating(AlarmManager.RTC_WAKEUP,time,0,pendingIntent);


        }
        else
        {
            ringtoneManager.cancel(pendingIntent);
            Toast.makeText(this, "No Alarm", Toast.LENGTH_SHORT).show();
        }
    }
}

