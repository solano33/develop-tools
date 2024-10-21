package com.solano.controller;

import com.solano.entity.HelloContext;
import com.solano.service.GrpcHelloClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/21 20:26
 */
@RestController
@RequestMapping(("/grpc/client"))
public class HelloController {

    @Resource
    private GrpcHelloClient grpcHelloClient;

    @PostMapping("/hello")
    public String sayHello(@RequestBody HelloContext context) {
        return grpcHelloClient.sayHello(context);
    }
}
