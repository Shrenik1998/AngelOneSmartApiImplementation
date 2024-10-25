package com.scaler.angelonesmartapidemo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponseDTO {
    private boolean status;
    private String message;
    private String errorcode;
    private Data data;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // This will ignore any unknown fields in the response
    public static class Data {
        private String jwtToken;
        private String refreshToken;
        private String feedToken;
        private String state;
    }
}