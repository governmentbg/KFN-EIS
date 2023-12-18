package com.fsc.external.portal.controllers.sign;

import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;

public record SigningPrepareResponse(ServiceRequestDocumentResponse document,
                                     String fileBase64,
                                     String hashBase64,
                                     String hashContentBase64,
                                     String hashSignBase64,
                                     String hashCertBase64,
                                     String [] certificateChainBase64) {
}
