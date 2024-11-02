package com.scaler.angelonesmartapidemo.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.angelonesmartapidemo.utils.CustomLocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HistoricalDataRequestDto {
    private String exchange;
    private String symboltoken;
    private String interval;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime fromdate;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime todate;

    private String token;
}
