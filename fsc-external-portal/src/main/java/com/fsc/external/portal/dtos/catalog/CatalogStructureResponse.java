package com.fsc.external.portal.dtos.catalog;

import java.io.Serializable;

public class CatalogStructureResponse implements Serializable {
    private Long childId;
    private Long orderNum;

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }
}
