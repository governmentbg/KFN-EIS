package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import javax.validation.constraints.NotNull;

public class PnlPublicRegisterDocumentDownloadRequest {

    private String fileReferenceStr;
    @NotNull
    private Long documentId;

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
