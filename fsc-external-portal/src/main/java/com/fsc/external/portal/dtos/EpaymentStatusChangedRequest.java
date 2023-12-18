package com.fsc.external.portal.dtos;

import com.fsc.common.addon.entity.accounting.payment.enums.EpaymentStatus;
import io.jmix.core.metamodel.datatype.impl.EnumUtils;

import java.time.LocalDateTime;

public class EpaymentStatusChangedRequest {

    private String id;
    private String status;
    private LocalDateTime changeTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EpaymentStatus getStatus() {
        return EnumUtils.fromId(EpaymentStatus.class, status, null);
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
        return "EpaymentStatusChangedRequest{"
                + "id = " + id
                + ", status=" + status
                + ", changeTime=" + changeTime
               + '}';
    }
}
