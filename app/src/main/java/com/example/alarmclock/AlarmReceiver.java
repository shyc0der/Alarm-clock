package com.example.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    if (uri==null){
        uri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    }
        Ringtone ringtone=RingtoneManager.getRingtone(context,uri);
    ringtone.play();

    }
}
