package com.phongdo.forwad.service;

import com.futa.proto.model.MessageReq;
import com.futa.proto.model.MessageRpl;
import com.futa.proto.service.core.FutaPayServiceGrpc;
import com.phongdo.dto.RequestDTO;
import com.phongdo.dto.ResponseDTO;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class GRPCClient {
    private final FutaPayServiceGrpc.FutaPayServiceBlockingStub blockingStub;

    public GRPCClient() {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("127.0.0.1:8083")
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
        try {
            MessageRpl response = blockingStub.exec(request);

            return new ResponseDTO(
                    response.getRequestId(),
                    response.getServiceId(),
                    response.getPartnerId(),
                    response.getType(),
                    response.getResponse(),
                    response.getResultCode(),
                    response.getResultMessage());

        } catch (StatusRuntimeException e) {
            return null;
        }
    }
}
