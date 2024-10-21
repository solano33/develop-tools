package com.solano;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/21 18:12
 */
@RestController
public class TestController {

    @Resource
    private WorkStorageApi workStorageApi;

    @GetMapping("test")
    public Map test() {
        return workStorageApi.getVersion();
    }
}
