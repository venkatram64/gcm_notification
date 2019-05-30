package com.venkat.sendgcmnofifications.service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.venkat.sendgcmnofifications.model.GCMRegistry;
import com.venkat.sendgcmnofifications.repository.GCMRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSenderService {

    @Autowired
    private GCMRegistryRepository registryRepository;

    @Autowired
    private Environment environment;

    public List<String> getRegisteredIds(){

        List<GCMRegistry> list = registryRepository.findAll();
        List<String> idList = new ArrayList<>();
        for(GCMRegistry gcm : list){
            idList.add(gcm.getGcmRegid());
        }
        return idList;
    }

    public String sendMessage(String msg) throws IOException {

        String gcmApiKey = environment.getProperty("gcm.api.key");
        String senderKey = environment.getProperty("gcm_sender_id");
        String fcmServerUrl = environment.getProperty("fcm_server_url");
        Sender sender = new Sender(gcmApiKey);

        List<String> idList = getRegisteredIds();


        Message message = new Message.Builder()
                .collapseKey("message")
                .timeToLive(3)
                .delayWhileIdle(true)
                .addData("message", "Welcome to Push Notifications from Venkatram") //you can get this message on client side app
                .build();

        if(idList.size() == 1){
            Result result = sender.send(message, idList.get(0), 1);
            if (result.getMessageId() != null) {
                System.out.println("Push Notification Sent Successfully");
            }else {
                System.out.println("ErrorCode " + result.getErrorCodeName());
            }
        }else{
            MulticastResult multicastResult = sender.send(message, idList, 0);
            for (Result r : multicastResult.getResults()) {
                if (r.getMessageId() != null) {
                    System.out.println("Push Notification Sent Successfully");
                }else {
                    System.out.println("ErrorCode " + r.getErrorCodeName());
                }
            }
        }
        return "Pushed the notifications to the server.";
    }


}
