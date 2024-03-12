package com.handson.mail.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.handson.mail.util.Dates;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

enum EventType {
    Registration,
    Arrival,
    Departure,
    Confirmation
}

@Entity
@Table(name = "event")
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Date createdAt = Dates.nowUTC();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdAt")
    public LocalDateTime calcCreatedAt() {
        return Dates.atLocalTime(createdAt);
    }

    @NotNull
    @JoinColumn(name = "trackingNumber")
    private Long trackingNumber;

    @NotNull
    @JoinColumn(name = "postOfficeId")
    private Long postOfficeId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public Long getEventId() {
        return eventId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Long getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(Long postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public static final class EventBuilder {
        private Long eventId;
        private Date createdAt = Dates.nowUTC();
        private Long trackingNumber;
        private Long postOfficeId;
        private EventType type;

        private EventBuilder() {
        }

        public static EventBuilder aEvent() {
            return new EventBuilder();
        }

        public EventBuilder eventId(Long eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventBuilder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EventBuilder trackingNumber(Long trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public EventBuilder postOfficeId(Long postOfficeId) {
            this.postOfficeId = postOfficeId;
            return this;
        }

        public EventBuilder type(EventType type) {
            this.type = type;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.setCreatedAt(createdAt);
            event.setTrackingNumber(trackingNumber);
            event.setPostOfficeId(postOfficeId);
            event.setEventType(type);
            return event;
        }
    }

}
