package com.fsc.fscintegrationepaymentservice.domain.external;

import java.time.LocalDateTime;

public class StatusChangeRequest {
    private String id;
    private String status;
    private LocalDateTime changeTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    @Override
    public String toString() {
        return "StatusChangeRequest{"
               + "id='" + id + '\''
               + ", status='" + status + '\''
               + ", changeTime=" + changeTime
               + '}';
    }
}
