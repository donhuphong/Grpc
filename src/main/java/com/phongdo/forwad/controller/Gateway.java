package com.phongdo.forwad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phongdo.dto.RequestDTO;
import com.phongdo.dto.ResponseDTO;
import com.phongdo.forwad.service.GRPCClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Gateway {

    private final GRPCClient grpcClient;

    public Gateway(GRPCClient grpcClient) {
        this.grpcClient = grpcClient;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test")
    public String createAdminBalance() {
        return "success";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/gateway")
    public ResponseDTO createAdminBalance(@RequestHeader Map<String, String> headers, @RequestBody String request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            RequestDTO requestDTO = mapper.readValue(request, RequestDTO.class);
            return grpcClient.send(requestDTO);
        } catch (Exception e) {
            return null;
        }
    }
}
