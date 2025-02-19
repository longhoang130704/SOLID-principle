package com.example.backend_tutorial.dto.ApiResponse;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerApiResponse {
    
    private int status;
    private String message;
    private Long id;
    private String cccd;
    private String name;
    private String email;
    private String phone;
    private String address;
}
