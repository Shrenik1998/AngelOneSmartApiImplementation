package com.scaler.angelonesmartapidemo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponseDto {
    private boolean status;
    private String message;
    private String errorcode;
    private UserInfo data;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserInfo {
        private String clientcode;
        private String name;
        private String email;
        private String mobileno;
        private List<String> exchanges;
        private List<String> products;
        private String lastlogintime;
        private String brokerid;
    }
}
