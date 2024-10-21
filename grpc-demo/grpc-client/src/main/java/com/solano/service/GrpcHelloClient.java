package com.solano.service;

import com.solano.entity.HelloContext;
import com.solano.grpc.common.Common;
import com.solano.grpc.service.GrpcHelloServiceGrpc;
import com.solano.grpc.service.Hello;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/21 20:21
 */
@Service
public class GrpcHelloClient {

    @GrpcClient("grpc-server")
    private GrpcHelloServiceGrpc.GrpcHelloServiceBlockingStub stub;

    public String sayHello(HelloContext context) {
        Common.GrpcBaseResponse response = stub.sayHello(Hello.GrpcHelloParam.newBuilder().setName(context.getName())
                .setCount(context.getCount())
                .setContext(context.getContext())
                .build());
        return response.getResponseMessage();
    }
}
