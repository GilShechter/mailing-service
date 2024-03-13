package com.handson.mail.repo;

import com.handson.mail.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    PackageRepository repository;

    public Iterable<Package> all() {
        return repository.findAll();
    }

    public Optional<Package> findById(Long trackingNumber) {
        return repository.findById(trackingNumber);
    }

    public Package save(Package pack) {
        return repository.save(pack);
    }

    public void delete(Package pack) {
        repository.delete(pack);
    }

    public List<Package> getAllConfirmed() { return repository.findAllByCurrentLocationEqualsDestinationAddress(); }

}
