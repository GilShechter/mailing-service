package com.handson.mail.controller;

import com.handson.mail.model.Event;
import com.handson.mail.model.EventIn;
import com.handson.mail.model.Package;
import com.handson.mail.repo.EventService;
import com.handson.mail.repo.PackageService;
import com.handson.mail.repo.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/packages")
public class EventController {
    @Autowired
    EventService eventService;

    @Autowired
    PackageService packageService;

    @Autowired
    PostOfficeService postOfficeService;

    @RequestMapping(value = "/{trackingNumber}/events", method = RequestMethod.POST)
    public ResponseEntity<?> insertEvent(@PathVariable Long trackingNumber, Long postOfficeId , @RequestBody EventIn eventIn){
        var pack = packageService.findById(trackingNumber);
        var postOffice = postOfficeService.findById(postOfficeId);
        if (pack.isEmpty())
            throw new RuntimeException("Package with tracking number: " + trackingNumber + " not found.");
        if (postOffice.isEmpty())
            throw new RuntimeException("Post Office with tracking number: " + postOfficeId + " not found.");
        Event event = eventIn.toEvent(trackingNumber, postOfficeId);
        event = eventService.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }



}
