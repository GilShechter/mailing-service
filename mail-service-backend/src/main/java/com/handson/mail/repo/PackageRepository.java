package com.handson.mail.repo;

import com.handson.mail.model.Package;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageRepository extends CrudRepository<Package, Long> {
    @Query("SELECT p FROM Package p WHERE p.currentLocation = p.destinationAddress")
    List<Package> findAllByCurrentLocationEqualsDestinationAddress();
}
