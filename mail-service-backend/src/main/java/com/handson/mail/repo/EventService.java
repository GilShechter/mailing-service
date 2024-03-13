package com.handson.mail.repo;

import com.handson.mail.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    public Iterable<Event> all() {
        return repository.findAll();
    }

    public Optional<Event> findById(Long eventId) {
        return repository.findById(eventId);
    }

    public Event save(Event event) {
        return repository.save(event);
    }

    public void delete(Event event) {
        repository.delete(event);
    }

    public List<Event> getAllEventsByTrackingNumber(Long trackingNumber) {
        return repository.findAllByTrackingNumber(trackingNumber);
    }

}