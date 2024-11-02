package com.scaler.angelonesmartapidemo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistoricalDataIOResponseDto {
    private boolean status;
    private String message;
    private String errorcode;
    private List<Data> data;

    @Getter
    @Setter
    public static class Data {
        private String time;
        private int oi;
    }
}
