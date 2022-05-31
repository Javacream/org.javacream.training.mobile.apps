package org.javacream.training.android.people.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import org.javacream.training.android.people.PeopleApplicationContext;
import org.javacream.training.android.people.PeopleListActivity;
import org.javacream.training.android.people.R;
import org.javacream.training.android.people.model.Person;

import java.io.Serializable;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PeopleListService extends IntentService {
    public PeopleListService() {
        super("PeopleListService");
    }

    private static final String CHANNEL_ID = "12";

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.i("SimpleService", "simple service startet");
            try {
                Thread.sleep(1000);
                List<Person> people = PeopleApplicationContext.peopleModel().findAll();
                sendNotification(people);
            } catch (InterruptedException e) {
                Log.e("SimpleService", "InterruptedException", e);
            }
            Log.i("SimpleService", "simple service finished, start notification");
        }
    }

    private void sendNotification(List<Person> people) {
        createNotificationChannel();
        // Pending intent to be fired when notification is clicked
        Intent intent = new Intent(this, PeopleListActivity.class);
        intent.setAction("ACTION");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("people", (Serializable) people);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 01,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle("Retrieved People List");
        builder.setContentText("Elements: " + people.size());
        //builder.setSubText("Sub Text");
        builder.setNumber(100);
        builder.setTicker("Notification by Service");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setChannelId(CHANNEL_ID);
        Notification notification = builder.build();
        NotificationManager notificationManger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManger.notify(01, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "agf";
            String description = "adsgagh";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}

