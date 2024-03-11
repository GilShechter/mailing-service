package com.handson.mail.controller;

import com.handson.mail.model.Package;
import com.handson.mail.model.PackageIn;
import com.handson.mail.repo.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    PackageService packageService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPackages(){
        return new ResponseEntity<>(packageService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{trackingNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getOnePackage(@PathVariable Long trackingNumber) {
        return new ResponseEntity<>(packageService.findById(trackingNumber), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> insertPackage(@RequestBody PackageIn packageIn) {
        Package pack = packageIn.toPackage();
        pack = packageService.save(pack);
        return new ResponseEntity<>(pack, HttpStatus.OK);
    }

    @RequestMapping(value = "/{trackingNumber}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePackage(@PathVariable Long trackingNumber, @RequestBody PackageIn packageIn) {
        Optional<Package> dbPackage = packageService.findById(trackingNumber);
        if (dbPackage.isEmpty())
            throw new RuntimeException("Package with tracking number: " + trackingNumber + " not found.");
        packageIn.updatePackage(dbPackage.get());
        Package updatedPackage = packageService.save(dbPackage.get());
        return new ResponseEntity<>(updatedPackage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{trackingNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePackage(@PathVariable Long trackingNumber) {
        Optional<Package> dbPackage = packageService.findById(trackingNumber);
        if (dbPackage.isEmpty())
            throw new RuntimeException("Package with tracking number " + trackingNumber + " not found.");
        packageService.delete(dbPackage.get());
        return new ResponseEntity<>("DELETED " + trackingNumber, HttpStatus.OK);
    }

}
