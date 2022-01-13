package com.example.iHaveToDo;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.iHaveToDo.Database.EntityClass;

public class ActionReciever extends AlarmBrodcast  {
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("id",0);
        int id1=intent.getIntExtra("id1",1);

        String action=intent.getStringExtra("action");
        if(action.equals("action1")){
            performAction1();
        }
        else if(action.equals("action2")){
            performAction2();

        }
        // Pour fermer le menu de notification
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);

        NotificationManager notificationManager =
                (NotificationManager) MyApplication.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
        NotificationManager notificationManager1 =
                (NotificationManager) MyApplication.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager1.cancel(id1);

    }

    public void performAction1(){
        EntityClass entityClass=new EntityClass();
        entityClass.setEventdone(true);
        Toast.makeText(context,"Done",Toast.LENGTH_LONG).show();
    }

    public void performAction2(){

    }
    }



