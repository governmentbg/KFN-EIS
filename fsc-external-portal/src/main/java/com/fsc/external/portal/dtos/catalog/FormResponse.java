package com.fsc.external.portal.dtos.catalog;

public class FormResponse {

    private String jsonForm;
    private String htmlForm;

    public FormResponse() {
    }

    public FormResponse(String jsonForm, String htmlForm) {
        this.jsonForm = jsonForm;
        this.htmlForm = htmlForm;
    }

    public String getJsonForm() {
        return jsonForm;
    }

    public void setJsonForm(String jsonForm) {
        this.jsonForm = jsonForm;
    }

    public String getHtmlForm() {
        return htmlForm;
    }

    public void setHtmlForm(String htmlForm) {
        this.htmlForm = htmlForm;
    }
}
