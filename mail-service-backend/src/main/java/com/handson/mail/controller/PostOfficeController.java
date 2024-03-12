package com.handson.mail.controller;

import com.handson.mail.model.Package;
import com.handson.mail.model.PackageIn;
import com.handson.mail.model.PostOffice;
import com.handson.mail.model.PostOfficeIn;
import com.handson.mail.repo.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/post-office")
public class PostOfficeController {

    @Autowired
    PostOfficeService postOfficeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPostOffices(){
        return new ResponseEntity<>(postOfficeService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOnePackage(@PathVariable Long id) {
        return new ResponseEntity<>(postOfficeService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> insertPackage(@RequestBody PostOfficeIn postOfficeIn) {
        PostOffice postOffice = postOfficeIn.toPostOffice();
        postOffice = postOfficeService.save(postOffice);
        return new ResponseEntity<>(postOffice, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePackage(@PathVariable Long id, @RequestBody PostOfficeIn postOfficeIn) {
        Optional<PostOffice> dbPostOffice = postOfficeService.findById(id);
        if (dbPostOffice.isEmpty())
            throw new RuntimeException("Post Office with tracking number: " + id + " not found.");
        postOfficeIn.updatePostOffice(dbPostOffice.get());
        PostOffice updatedPostOffice = postOfficeService.save(dbPostOffice.get());
        return new ResponseEntity<>(updatedPostOffice, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePackage(@PathVariable Long id) {
        Optional<PostOffice> dbPostOffice = postOfficeService.findById(id);
        if (dbPostOffice.isEmpty())
            throw new RuntimeException("Post Office with tracking number " + id + " not found.");
        postOfficeService.delete(dbPostOffice.get());
        return new ResponseEntity<>("DELETED " + id, HttpStatus.OK);
    }

}