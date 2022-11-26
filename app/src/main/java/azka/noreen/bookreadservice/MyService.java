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
Boolean started=false;

    Book book;
    public MyService() {
    }
    public static void launchSecondService(Context context) {
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(ACTION_Launch_Activity);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() != null) {
            String action = intent.getAction();
            if (action.equals(ACTION_Launch_Activity)) {
                started=true;
                return launchNotification();
            }
            if(started){
            if(action.equals(ACTION_OPEN_DETAILS))
            {
                openDetails();
            }
            else if(action.equals("updateNotification"))
            {
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Book bookClicked = (Book) intent.getSerializableExtra("book");
//                String value = intent.getStringExtra("sname");
                mNotificationManager.notify(notificaitionid,createNotification(bookClicked));
            }}
        }
        else
            return START_STICKY;
        return START_STICKY;
    }
    private void setBook(Book nm){
        book=nm;
    }
    private Book getBook(){
        return book;
    }
    private void openDetails() {
        Intent intent = new Intent(MyService.this, Details.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra("bookDetails",getBook());
        startActivity(intent);
    }
    private int launchNotification() {
        createNotificationChannel();
        startForeground(notificaitionid, createNotification(new Book(0,"ded","this is  started","12")));
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        String sna= intent.getStringExtra("sname");
//        return mBinder;
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private android.app.Notification createNotification(Book book) {
        Intent intent = new Intent(MyService.this, MyService.class);
        //for not opening the activity again and again
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        RemoteViews customNotification = new RemoteViews(getPackageName(), R.layout.customnotification);
        customNotification.setTextViewText(R.id.play,book.getName()+book.getBookId());
        customNotification.setOnClickPendingIntent(R.id.play, getButtonPendingIntent(ACTION_OPEN_DETAILS, book));
//        customNotification.setOnClickPendingIntent(R.id.stop, getButtonPendingIntent(ACTION_Stop_Button));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, "My_App")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContent(customNotification);


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(MyService.this);
        notificationManagerCompat.notify(notificaitionid,builder.build());

        return builder.build();// notification object
    }
    private PendingIntent getButtonPendingIntent(String action,Book book) {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(action);
        setBook(book);
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