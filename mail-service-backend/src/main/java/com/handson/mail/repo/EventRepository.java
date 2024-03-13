package com.handson.mail.repo;

import com.handson.mail.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByTrackingNumber(Long trackingNumber);
}
