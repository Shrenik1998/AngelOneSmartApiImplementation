package com.scaler.angelonesmartapidemo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistoricalDataResponseDto {
    private boolean status;
    private String message;
    private String errorcode;
    private List<List<Object>> data;
}
