package com.example.backend_tutorial.dto.ApiRequest;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CustomerApiRequest {
    private String cccd;
    private String name;
    private String email;
    private String phone;
    private String address;
}
