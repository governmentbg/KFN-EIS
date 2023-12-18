package com.fsc.external.portal.controllers.myservices;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum MyReportsStatus implements EnumClass<String> {

    ACCEPTED("A"),          // входиран(а)
    AWAITS_PROCESSING("W"), // oчаква обработка
    CORRECTION("C"),        // Корекция
    CORRECTED("M");         // Коригиран

    private String id;

    MyReportsStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static MyReportsStatus fromId(String id) {
        for (MyReportsStatus at : MyReportsStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}