package com.phongdo.forwad.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futa.proto.model.MessageReq;
import com.futa.proto.model.MessageRpl;
import com.futa.proto.service.core.FutaPayServiceGrpc;
import com.phongdo.dto.RequestDTO;
import com.phongdo.dto.ResponseDTO;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class GRPCClient {
    private final FutaPayServiceGrpc.FutaPayServiceBlockingStub blockingStub;
    private static final String IP = "127.0.0.1";
    private static final String PORT = "8082";

    public GRPCClient() {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget(IP + ":" + PORT)
                .usePlaintext()
                .build();
        this.blockingStub = FutaPayServiceGrpc.newBlockingStub(channel);
    }

    public ResponseDTO send(RequestDTO requestDTO) {

        MessageReq request =
                MessageReq.newBuilder()
                        .setRequestId(requestDTO.getRequestId())
                        .setServiceId(requestDTO.getServiceId())
                        .setPartnerId(requestDTO.getPartnerId())
                        .setType(requestDTO.getType())
                        .setRequest(requestDTO.getRequest().toString())
                        .putAllHeaders(new TreeMap<>())
                        .setUserProfile(requestDTO.getUserProfile())
                        .build();

        MessageRpl response = blockingStub.exec(request);
        String resp = response.getResponse();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode body = null;
        try {
            body = mapper.readTree(resp);
        } catch (Exception e) {
            //
        }
        return new ResponseDTO(
                response.getRequestId(),
                response.getServiceId(),
                response.getPartnerId(),
                response.getType(),
                body,
                response.getResultCode(),
                response.getResultMessage());
    }
}
