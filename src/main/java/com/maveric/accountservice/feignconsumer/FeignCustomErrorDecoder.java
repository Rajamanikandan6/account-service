package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.exception.BalanceFeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import feign.Util;
import feign.codec.ErrorDecoder;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class FeignCustomErrorDecoder implements ErrorDecoder {
    @Override public Exception decode(String methodKey, Response response) {
        byte[] bodyData = new byte[0];
        System.out.println(response);
        try {
            bodyData = Util.toByteArray(response.body().asInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseBody = new String(bodyData);
        JSONObject responseObj = new JSONObject(responseBody);
        String message =responseObj.getString("message");

        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            System.out.println(message);
            return new BalanceFeignException(message);
        } else if (responseStatus.is4xxClientError()) {
            return new BalanceFeignException(message);
        } else {
            return new Exception("Generic exception");
        }
    }
}
