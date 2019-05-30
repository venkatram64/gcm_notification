package com.venkat.sendgcmnofifications.repository;


import com.venkat.sendgcmnofifications.model.GCMRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GCMRegistryRepository extends JpaRepository<GCMRegistry, Integer> {
}
