package com.solano.service;

import com.solano.grpc.common.Common;
import com.solano.grpc.service.GrpcHelloServiceGrpc;
import com.solano.grpc.service.Hello;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/21 20:15
 */
@Service
public class GrpcHelloService extends GrpcHelloServiceGrpc.GrpcHelloServiceImplBase {

    @Override
    public void sayHello(Hello.GrpcHelloParam request, StreamObserver<Common.GrpcBaseResponse> responseObserver) {
        Common.GrpcBaseResponse response = Common.GrpcBaseResponse.newBuilder()
                .setCode(200)
                .setResponseMessage(String.format("hello %s! I am grpc-server. I receive, count: %d, context: %s",
                        request.getName(), request.getCount(), request.getContext()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
