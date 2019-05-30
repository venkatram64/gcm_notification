package com.venkat.sendgcmnofifications.controller;

import com.venkat.sendgcmnofifications.model.GCMRegistry;
import com.venkat.sendgcmnofifications.service.GCMRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/gcm")
public class GCMRegistryController {

    @Autowired
    private GCMRegistryService registryService;

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
}
