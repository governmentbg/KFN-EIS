package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import io.jmix.core.FileRef;

public class PublicRegisterDocumentsDto {

    private String documentFile;
    private String documentNumber;
    private String documentType;
    private Long documentId;
    private String documentText;
    private FileRef fileRef;
    private String fileReferenceStr;

    public String getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(String documentFile) {
        this.documentFile = documentFile;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    public FileRef getFileRef() {
        return fileRef;
    }

    public void setFileRef(FileRef fileRef) {
        this.fileRef = fileRef;
    }

    public String getFileReferenceStr() {
        return fileReferenceStr;
    }

    public void setFileReferenceStr(String fileReferenceStr) {
        this.fileReferenceStr = fileReferenceStr;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }
}
