package com.example.iHaveToDo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

public class CancelNotificationReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int id=intent.getIntExtra("id1",1);
   String command=  intent.getStringExtra("command");
     if (TextUtils.equals(command,"cancel_last"))
     {
         NotificationManager notificationManager1 =
                 (NotificationManager) MyApplication.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
notificationManager1.cancel(id);
     }
    }
}
