package com.phongdo.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class ResponseDTO {
    private String requestId;
    private String serviceId;
    private String partnerId;
    private String type;
    private JsonNode response;
    private int resultCode;
    private String resultMessage;

    public ResponseDTO(String requestId, String serviceId, String partnerId, String type, JsonNode response, int resultCode, String resultMessage) {
        this.requestId = requestId;
        this.serviceId = serviceId;
        this.partnerId = partnerId;
        this.type = type;
        this.response = response;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getResponse() {
        return response;
    }

    public void setResponse(JsonNode response) {
        this.response = response;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
