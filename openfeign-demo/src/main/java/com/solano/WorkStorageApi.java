package com.solano;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/21 17:56
 */
@FeignClient(name = "workStorageApi", url = "https://public-workstorage-hdmap.evad.mioffice.cn")
public interface WorkStorageApi {

    @GetMapping("/api/work/main/getVersion")
    Map getVersion();
}
