package com.fsc.external.portal.controllers;

import com.fsc.external.portal.mappers.services.OpenDataPnlTypeMapper;
import com.fsc.external.portal.services.OpenDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/open-data/pnl")
public class OpenDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenDataController.class);
    @Autowired
    private OpenDataPnlTypeMapper openDataPnlTypeMapper;

    @Autowired
    private OpenDataService openDataService;
    
    @GetMapping(value = "/{pnlTypeIdString}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String renderOpenDataFile(@PathVariable String pnlTypeIdString) {
        Long pnlTypeId = openDataPnlTypeMapper.getDescription(pnlTypeIdString);

        LOGGER.info("Received request for pnlTypeId: {} (pnlTypeIdString: {})", pnlTypeId, pnlTypeIdString);

        String result = openDataService.getPnlDataByPnlTypeId(pnlTypeId);

        LOGGER.info("Data retrieved successfully for pnlTypeId: {} (pnlTypeIdString: {})", pnlTypeId, pnlTypeIdString);

        return result;
    }
}
