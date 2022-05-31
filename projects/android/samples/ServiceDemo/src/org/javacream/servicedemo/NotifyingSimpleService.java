package org.javacream.servicedemo;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotifyingSimpleService extends IntentService {

	public NotifyingSimpleService() {
		super("NotifiyingService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("SimpleService", "simple service startet");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Log.e("SimpleService", "InterruptedException", e);
		}
		Log.i("SimpleService", "simple service finished, start notification");
		sendNotification();
	}

	private void sendNotification() {
		// Pending intent to be fired when notification is clicked
		Intent intent = new Intent(this, Activity3.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 01,
				intent, Intent.FLAG_ACTIVITY_NEW_TASK);

		// Get the builder to create notification.
		Builder builder = new Notification.Builder(getApplicationContext());
		// Set the first line of text in the platform notification template.
		builder.setContentTitle("Content Title");
		// Set the second line of text in the platform notification
		// template.
		builder.setContentText("Content Text");
		// Set the third line of text in the platform notification template.
		// Don't use if you're also using setProgress(int, int, boolean);
		// they occupy the same location in the standard template.
		builder.setSubText("Sub Text");
		// Set the large number at the right-hand side of the notification.
		// This is equivalent to setContentInfo, although it might show the
		// number in a different font size for readability.
		builder.setNumber(100);
		// Set the "ticker" text which is displayed in the status bar when
		// the notification first arrives.
		builder.setTicker("Notification by Service");
		// Set the small icon resource, which will be used to represent the
		// notification in the status bar. The platform template for the
		// expanded view will draw this icon in the left, unless a large
		// icon has also been specified, in which case the small icon will
		// be moved to the right-hand side.
		builder.setSmallIcon(R.drawable.ic_launcher);
		// Supply a PendingIntent to send when the notification is cleared
		// explicitly by the user.
		builder.setContentIntent(pendingIntent);
		// Make this notification automatically dismissed when the user
		// touches it. The PendingIntent set with
		// setDeleteIntent(PendingIntent) will be sent when this happens.
		builder.setAutoCancel(true);
		// Set the priority of this notification.
		builder.setPriority(0);

		// Combine all of the options that have been set and return a new
		// Notification object.
		Notification notification = builder.build();

		NotificationManager notificationManger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// Post a notification to be shown in the status bar. If a
		// notification with the same id has already been posted by your
		// application and has not yet been canceled, it will be replaced by
		// the updated information.
		notificationManger.notify(01, notification);

	}

}
