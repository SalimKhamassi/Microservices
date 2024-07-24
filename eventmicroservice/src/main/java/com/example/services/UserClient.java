package com.example.services;

import com.example.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*@FeignClient(name = "user-service", url = "http://localhost:8089")*/
@FeignClient(name = "user-query-service")
public interface UserClient {
    @GetMapping("/queryusers/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
