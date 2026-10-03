package com.example.NotificationChannel.stratergy;

import java.util.HashMap;
import java.util.Map;


public class NotificaionService {

    Map<String,NotificationStratergy> mp = new HashMap<>();


    public NotificaionService(){
    mp.put("SMS", new SMSStratergy());
    mp.put("Email",new EmailStratergy());
    }

    public void sendNotification(String type,String message){

        if(!mp.containsKey(type)) return;

        NotificationStratergy notificationStratergy  = mp.get(type);
        notificationStratergy.sendNotification(message);
    }
}
