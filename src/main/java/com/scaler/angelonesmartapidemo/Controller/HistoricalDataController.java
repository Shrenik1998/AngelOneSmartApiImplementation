package com.scaler.angelonesmartapidemo.Controller;


import com.scaler.angelonesmartapidemo.Service.HistoricalDataService;
import com.scaler.angelonesmartapidemo.dtos.HistoricalDataIOResponseDto;
import com.scaler.angelonesmartapidemo.dtos.HistoricalDataRequestDto;
import com.scaler.angelonesmartapidemo.dtos.HistoricalDataResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HistoricalData")
public class HistoricalDataController {

    @Autowired
    private HistoricalDataService historicalDataService;

    @PostMapping("/getCandleData")
    public HistoricalDataResponseDto getCandleData(@RequestBody HistoricalDataRequestDto historicalDataRequestDto,
                                                   @RequestParam String privateKey ) throws Exception {

        return historicalDataService.getCandleData(historicalDataRequestDto.getExchange(),
                historicalDataRequestDto.getSymboltoken(), historicalDataRequestDto.getInterval(),
                historicalDataRequestDto.getFromdate(),historicalDataRequestDto.getTodate(),
                historicalDataRequestDto.getToken(),privateKey);
    }

    @PostMapping("/getIOData")
    public HistoricalDataIOResponseDto getIOData(@RequestBody HistoricalDataRequestDto historicalDataRequestDto,
                                                 @RequestParam String privateKey ) throws Exception {

        return historicalDataService.getIOData(historicalDataRequestDto.getExchange(),
                historicalDataRequestDto.getSymboltoken(), historicalDataRequestDto.getInterval(),
                historicalDataRequestDto.getFromdate(),historicalDataRequestDto.getTodate(),
                historicalDataRequestDto.getToken(),privateKey);
    }
}
