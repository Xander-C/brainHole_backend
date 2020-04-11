package com.xander.flutter_backend.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.springframework.stereotype.Service;


@Service
public class JiGuangPushUtil {

    private JPushClient jPushClient=new JPushClient("f","2f7c9abd2e325f3df5c73a46");//Todo: 这里填极光的masterSecret与appKey

    public void pushNotice(String type,String value,String alert){
        Builder builder= PushPayload.newBuilder();
        builder.setPlatform(Platform.all());
        Options options=Options.sendno();
        options.setTimeToLive(86400);
        builder.setOptions(options);
        if(type.equals("alias")){
            builder.setAudience(Audience.alias(value));
        }else if(type.equals("tag")){
            builder.setAudience(Audience.tag(value));
        }else if(type.equals("regId")){
            builder.setAudience(Audience.registrationId(value));
        }else{
            builder.setAudience(Audience.all());
        }
        builder.setNotification(Notification.alert(alert));
        PushPayload pushPayload=builder.build();
        try{
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}