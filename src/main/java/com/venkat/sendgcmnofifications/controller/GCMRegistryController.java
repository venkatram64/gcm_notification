package com.venkat.sendgcmnofifications.controller;

import com.venkat.sendgcmnofifications.model.GCMRegistry;
import com.venkat.sendgcmnofifications.model.Message;
import com.venkat.sendgcmnofifications.service.GCMRegistryService;
import com.venkat.sendgcmnofifications.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/gcm")
public class GCMRegistryController {

    @Autowired
    private GCMRegistryService registryService;

    @Autowired
    private MessageSenderService messageSenderService;

    @GetMapping(value = "/all", headers="Accept=application/json")
    public List<GCMRegistry> getAllGCMRegistry(){
        return registryService.findAllGCMRegistry();
    }

    /*
    {
    "gcmRegid": "r09trtrtr67676767"
    }
     */
    @PostMapping(value="/create" ,headers="Accept=application/json")
    public ResponseEntity<Void> createGCMRegistry(@RequestBody GCMRegistry gcmRegistry, UriComponentsBuilder ucBuilder){
        System.out.println("Creating gcm registry "+gcmRegistry.getGcmRegid());
        registryService.createGCMRegistry(gcmRegistry);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/gcmRegistry/{id}").buildAndExpand(gcmRegistry.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value="/send-message" ,headers="Accept=application/json")
    public ResponseEntity<Void> sendMessage(@RequestBody Message msg){
        System.out.println("Creating gcm registry "+msg.getMsg());
        String message = "";
        try {
            message = messageSenderService.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
