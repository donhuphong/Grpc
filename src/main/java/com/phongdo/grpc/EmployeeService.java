package com.phongdo.grpc;

import com.phong.grpc.EmployeeRequest;
import com.phong.grpc.EmployeeResponse;
import com.phong.grpc.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Override
    public void getEmployee(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        EmployeeResponse response = EmployeeResponse.newBuilder()
                .setEmpId("Hi " + request.getEmpId() + ", you order has been executed")
                .build();

        System.out.println(response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
