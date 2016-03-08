package com.example.carlos.exercisebuddy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by carlosyoung on 4/8/15.
 */
public class MyNotificationService extends Service{
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
<<<<<<< Updated upstream
        Toast.makeText(this,"onCreate()",Toast.LENGTH_SHORT).show();
=======
>>>>>>> Stashed changes
    }

    @Override
    public  void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent,startId);
<<<<<<< Updated upstream
        Toast.makeText(this,"onStart()",Toast.LENGTH_SHORT).show();
=======
>>>>>>> Stashed changes
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        int icon = R.drawable.ic_exercisebuddy;
        String tickerText = "Time to Exercise";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon,tickerText,when);
        String contentTitle = "Exercise Buddy";
        String contentText = "Lets Workout";
        notification.setLatestEventInfo(this,contentTitle,contentText,pendingIntent);
        notificationManager.notify(123,notification);
    }
}
