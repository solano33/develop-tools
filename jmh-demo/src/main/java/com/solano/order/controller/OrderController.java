package com.solano.order.controller;

import com.solano.order.entity.response.BaseResponse;
import com.solano.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author solano
 * @date 2024/7/22 19:34
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/update")
    public BaseResponse updateOrder(@RequestBody Integer orderId) {
        return orderService.updateOrderWithLock(orderId);
    }
}
