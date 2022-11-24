package azka.noreen.bookreadservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {
    public static String ACTION_Launch_Activity = "Launch_Activity";
    public static String ACTION_OPEN_DETAILS = "open_details";
public int notificaitionid=122;
    String namee;
    public MyService() {
    }
    public static void launchSecondService(Context context) {
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(ACTION_Launch_Activity);
        context.startService(intent);
    }
    public void passNmae(String name) {
        namee=name;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() != null) {
            String action = intent.getAction();
            if (action.equals(ACTION_Launch_Activity)) {
//                RecyclerViewAdapter rva=new RecyclerViewAdapter();
//        rva.setMyInterface(new MyInterface() {
//            @Override
//            public void onStudentClick( Student student) {
//                name=student.getName();
////                Toast.makeText(MyService.this, student.getPhone(), Toast.LENGTH_SHORT).show();
//            }
//        });
                return launchNotification();
            }
            else if(action.equals(ACTION_OPEN_DETAILS))
               openDetails();
            else if(action.equals("getName"))
            {
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String value = intent.getStringExtra("sname");
                openDetails2(value);
                mNotificationManager.notify(notificaitionid,createNotification(value));

            }

        }
        return START_STICKY;
    }
    private void openDetails2(String val) {
        Intent intent = new Intent(MyService.this, Details.class);
        intent.putExtra("sn",val);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

        startActivity(intent);

        Toast.makeText(this, "Stop CLicked", Toast.LENGTH_SHORT).show();
    }
    private void openDetails() {
        Intent intent = new Intent(MyService.this, Details.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

        startActivity(intent);

        Toast.makeText(this, "Stop CLicked", Toast.LENGTH_SHORT).show();
    }
    private int launchNotification() {
        createNotificationChannel();
        startForeground(notificaitionid, createNotification("this is  started"));
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        String sna= intent.getStringExtra("sname");
//        return mBinder;
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private android.app.Notification createNotification(String value) {
        Intent intent = new Intent(MyService.this, MyService.class);
        //for not opening the activity again and again
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        RemoteViews customNotification = new RemoteViews(getPackageName(), R.layout.customnotification);
        customNotification.setTextViewText(R.id.play,value);
        customNotification.setOnClickPendingIntent(R.id.play, getButtonPendingIntent(ACTION_OPEN_DETAILS));
//        customNotification.setOnClickPendingIntent(R.id.stop, getButtonPendingIntent(ACTION_Stop_Button));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, "My_App")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("This is my Notification")
                .setContentText(value)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContent(customNotification);


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(MyService.this);
        notificationManagerCompat.notify(notificaitionid,builder.build());

        return builder.build();// notification object
    }
    private PendingIntent getButtonPendingIntent(String action) {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(action);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "This is my channel";
            String description = "this is description of chennal";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("My_App", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}