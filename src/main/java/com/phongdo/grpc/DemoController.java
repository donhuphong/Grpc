package com.phongdo.grpc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final DemoGRPCClientd grpcClient;

    public DemoController(DemoGRPCClientd grpcClient) {
        this.grpcClient = grpcClient;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ind")
    public void createAdminBalance(){
//        grpcClient.send("phongnhu");
    }
}
