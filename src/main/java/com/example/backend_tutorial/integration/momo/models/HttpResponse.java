package com.example.backend_tutorial.integration.momo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.Headers;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpResponse {
    int status;
    String data;
    Headers Headers;
}
