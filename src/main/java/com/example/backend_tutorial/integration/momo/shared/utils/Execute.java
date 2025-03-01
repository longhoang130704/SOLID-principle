package com.example.backend_tutorial.integration.momo.shared.utils;

import java.io.IOException;

import com.example.backend_tutorial.integration.momo.models.HttpRequest;
import com.example.backend_tutorial.integration.momo.models.HttpResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class Execute {

    OkHttpClient client = new OkHttpClient();

    public com.example.backend_tutorial.integration.momo.models.HttpResponse sendToMoMo(String endpoint, String payload) {

        try {

            HttpRequest httpRequest = new HttpRequest("POST", endpoint, payload, "application/json");

            Request request = createRequest(httpRequest);

            LogUtils.debug("[HttpPostToMoMo] Endpoint:: " + httpRequest.getEndpoint() + ", RequestBody:: " + httpRequest.getPayload());

            Response result = client.newCall(request).execute();
            HttpResponse response = new HttpResponse(result.code(), result.body().string(), result.headers());

            LogUtils.info("[HttpResponseFromMoMo] " + response.toString());

            return response;
        } catch (Exception e) {
            LogUtils.error("[ExecuteSendToMoMo] "+ e);
        }

        return null;
    }

    public static Request createRequest(HttpRequest request) {
        RequestBody body = RequestBody.create(MediaType.get(request.getContentType()), request.getPayload());
        return new Request.Builder()
                .method(request.getMethod(), body)
                .url(request.getEndpoint())
                .build();
    }

    public String getBodyAsString(Request request) throws IOException {
        Buffer buffer = new Buffer();
        RequestBody body = request.body();
        body.writeTo(buffer);
        return buffer.readUtf8();
    }
}

