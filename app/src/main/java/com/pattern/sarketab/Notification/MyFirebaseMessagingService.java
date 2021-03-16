package com.pattern.sarketab.Notification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pattern.sarketab.ui.main.MainActivity;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgingService";
    private static final String TITLE = "title";
    private static final String EMPTY = "";
    private static final String MESSAGE = "message";
    private static final String IMAGE = "image";
    private static final String ACTION = "action";
    private static final String DATA = "data";
    private static final String ACTION_DESTINATION = "action_destination";
    private static final String TOPIC_GLOBAL = "global";
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL);
        Log.d(TAG, "Refreshed token: " + s);
        sendRegistrationToServer(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();
            handleData(data);

        } else if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification());
        }// Check if message contains a notification payload.

    }

    private void handleNotification(RemoteMessage.Notification RemoteMsgNotification) {


        String message = RemoteMsgNotification.getBody();
        String title = RemoteMsgNotification.getTitle();
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitle(title);
        notificationVO.setMessage(message);
        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        SharedPreferences prefsUser = getSharedPreferences("User", MODE_PRIVATE);
        if (prefsUser.getInt("notification",0)==0)
        notificationUtils.displayNotification(notificationVO, resultIntent);
        notificationUtils.playNotificationSound();
    }

    private void handleData(Map<String, String> data) {
        String title = data.get(TITLE);
        String message = data.get(MESSAGE);
        String iconUrl = data.get(IMAGE);
        String action = data.get(ACTION);
        String actionDestination = data.get(ACTION_DESTINATION);
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitle(title);
        notificationVO.setMessage(message);
        notificationVO.setIconUrl(iconUrl);
        notificationVO.setAction(action);
        notificationVO.setActionDestination(actionDestination);
        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.displayNotification(notificationVO, resultIntent);
        notificationUtils.playNotificationSound();
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}