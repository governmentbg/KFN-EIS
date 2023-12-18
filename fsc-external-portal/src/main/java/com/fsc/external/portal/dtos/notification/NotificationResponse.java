package com.fsc.external.portal.dtos.notification;

import io.jmix.notifications.NotificationReadStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationResponse {
    private UUID id;
    private String type;
    private String subject;
    private String body;
    private NotificationReadStatus readStatus;
    private LocalDateTime createdDate;
    private String recipient;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public NotificationReadStatus getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(NotificationReadStatus readStatus) {
        this.readStatus = readStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
