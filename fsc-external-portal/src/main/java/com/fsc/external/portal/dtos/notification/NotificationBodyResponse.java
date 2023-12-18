package com.fsc.external.portal.dtos.notification;

public class NotificationBodyResponse {
    private int personId;
    private int correspondenceId;
    private int documentId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCorrespondenceId() {
        return correspondenceId;
    }

    public void setCorrespondenceId(int correspondenceId) {
        this.correspondenceId = correspondenceId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
}
