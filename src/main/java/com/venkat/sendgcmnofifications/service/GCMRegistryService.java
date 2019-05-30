package com.venkat.sendgcmnofifications.service;

import com.venkat.sendgcmnofifications.model.GCMRegistry;
import com.venkat.sendgcmnofifications.repository.GCMRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GCMRegistryService {

    @Autowired
    private GCMRegistryRepository registryRepository;

    public GCMRegistry createGCMRegistry(GCMRegistry gcmRegistry){
        return registryRepository.save(gcmRegistry);
    }

    public List<GCMRegistry> findAllGCMRegistry(){
        return registryRepository.findAll();
    }
}
