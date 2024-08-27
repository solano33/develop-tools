package com.solano.order.service;

import com.solano.order.entity.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author solano
 * @date 2024/7/22 19:54
 */
@Slf4j
@Service
public class OrderService {

    private static final Random random = new Random();

    public BaseResponse updateOrderWithLock(Integer orderId) {
        if (orderId <= 0) {
            return BaseResponse.success();
        }
        updateOrder(orderId);
        return BaseResponse.success();
    }

    /**
     * 这里模拟订单更新操作
     */
    private void updateOrder(Integer orderId) {
        log.info("【订单】开始更新订单, orderId: {}, thread: {}", orderId, Thread.currentThread());
        try {
            Thread.sleep(random.nextInt(2000) + 2000);
        } catch (InterruptedException ignored) {
        }
        log.info("【订单】结束更新订单, orderId: {}, thread: {}", orderId, Thread.currentThread());
    }
}
