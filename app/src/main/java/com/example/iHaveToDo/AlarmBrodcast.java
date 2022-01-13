package com.example.iHaveToDo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.iHaveToDo.Database.DatabaseClass;
import com.example.iHaveToDo.Database.EntityClass;

public class AlarmBrodcast extends BroadcastReceiver {
    EntityClass entityClass1 = new EntityClass();

    @Override
    public void onReceive(Context context, Intent intent) {
        final int NOTIFICACATION_ID = 1;
        Bundle bundle = intent.getExtras();
        String text = bundle.getString("event");
        String date = bundle.getString("date");
        String time = bundle.getString("time");
        int id = bundle.getInt("id");
        Log.e("okkkk", id + " id");


        Bundle bundle1 = new Bundle();
        bundle1.putString("date1", date);
        bundle1.putString("time1", time);


        EntityClass entityClass = new EntityClass();
        entityClass.setEventdone(true);
        entityClass.setEventtime(date);
        entityClass.setEventname(text);
        entityClass.setEventdate(time);
        DatabaseClass databaseClass = DatabaseClass.getDatabase(context);
//        databaseClass.EventDao().update(true,"broadcast1",1);


        databaseClass.EventDao().addAlarm(entityClass);


        // la Notification
        Intent intent1 = new Intent(context, com.example.iHaveToDo.NotificationMessage.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("message", text);

        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        Intent intent2 = new Intent(context, com.example.iHaveToDo.CreateEvent.class);
        Log.e("mn", bundle1.toString());
        // intent2.putExtras(bundle1);
        intent2.putExtra("time1", time);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        Intent intent3 = new Intent(context, ActionReciever.class);
        intent3.putExtra("action", "actionName");
        intent3.putExtra("id", 0);


        // Notification : création
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 1, intent2, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 1, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(context, 0, intent2, 0);
        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, 0, intent3, 0);
        contentView.setOnClickPendingIntent(R.id.flashButton, pendingSwitchIntent);
        contentView.setOnClickPendingIntent(R.id.schedule, pendingIntent1);
        contentView.setOnClickPendingIntent(R.id.done, pendingIntent2);
        contentView.setTextViewText(R.id.message, text);
        contentView.setTextViewText(R.id.date, date);
        contentView.setTextViewText(R.id.time2, time);
        mBuilder.setSmallIcon(R.drawable.ic_alarm_white_24dp);
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);

        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;


        // mBuilder.addAction(R.drawable.ic_schedule,"Reschedule",pendingIntent1);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContent(contentView);
        mBuilder.setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            //mBuilder.setAutoCancel(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }


        Notification notification = mBuilder.build();

        notificationManager.notify(NOTIFICACATION_ID, notification);

        // notificationManager.cancel(NOTIFICACATION_ID);

        //   notificationManager1.cancel(id1);


    }

    public void AlarmDone() {
        entityClass1.setEventdone(true);
    }
}