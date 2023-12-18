package com.fsc.external.portal.controllers.myservices;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum MyServicesStatus implements EnumClass<String> {

    REQUESTED("R"),         // заявен(а)
    ACCEPTED("A"),          // входиран(а)
    AWAITS_PROCESSING("W"); // oчаква обработка

    private String id;

    MyServicesStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static MyServicesStatus fromId(String id) {
        for (MyServicesStatus at : MyServicesStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}