package com.example.services;

import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRestTemplateService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    public List<UserDto> getAllUsers() {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-query-service");
        if (instances.isEmpty()) throw new RuntimeException("User query service not found");
        String userServiceUrl = instances.get(0).getUri().toString();

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                userServiceUrl + "/queryusers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }
}
