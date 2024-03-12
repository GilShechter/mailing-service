package com.handson.mail.model;

import com.handson.mail.util.Dates;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static com.handson.mail.model.Event.EventBuilder.aEvent;

public class EventIn {

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public Event toEvent(Long trackingNumber, Long postOfficeId) {
        return aEvent().createdAt(Dates.nowUTC()).trackingNumber(trackingNumber).postOfficeId(postOfficeId).type(eventType)
                .build();
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
