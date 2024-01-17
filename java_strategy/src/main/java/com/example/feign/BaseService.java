package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "base", url = "${feign.debug.url.system:}")
public interface BaseService {
}
