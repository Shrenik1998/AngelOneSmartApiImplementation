package com.scaler.angelonesmartapidemo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.angelonesmartapidemo.dtos.HistoricalDataIOResponseDto;
import com.scaler.angelonesmartapidemo.dtos.HistoricalDataResponseDto;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HistoricalDataService {

    @Autowired
    private NetworkInfoService networkInfoService;

    private static final String GET_CANDLE_DATA_URL = "https://apiconnect.angelone.in/rest/secure/angelbroking/historical/v1/getCandleData";
    private static final String GET_IO_DATA_URL = "https://apiconnect.angelone.in/rest/secure/angelbroking/historical/v1/getOIData";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HistoricalDataResponseDto getCandleData(String exchange, String symboltoken, String interval, LocalDateTime fromdate,
                                                   LocalDateTime todate, String token, String privateKey) throws Exception {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // Format the dates into the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fromDateFormatted = fromdate.format(formatter);
        String toDateFormatted = todate.format(formatter);

        // Create the request body
        String requestBody = "{\n" +
                "   \"exchange\": \"" + exchange + "\",\n" +
                "   \"symboltoken\": \"" + symboltoken + "\",\n" +
                "   \"interval\": \"" + interval + "\",\n" +
                "   \"fromdate\": \"" + fromDateFormatted + "\",\n" +
                "   \"todate\": \"" + toDateFormatted + "\"\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        // Build the request
        Request request = new Request.Builder()
                .url(GET_CANDLE_DATA_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + token)  // Add Authorization token
                .addHeader("X-PrivateKey", privateKey)  // Use the provided privateKey
                .addHeader("Accept", "application/json")
                .addHeader("X-SourceID", "WEB")
                .addHeader("X-ClientLocalIP", networkInfoService.getLocalIPAddress())  // Get Client Local IP
                .addHeader("X-ClientPublicIP", networkInfoService.getPublicIPAddress()) // Get Client Public IP
                .addHeader("X-MACAddress", networkInfoService.getMACAddress())         // Get MAC Address
                .addHeader("X-UserType", "USER")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();

                // Parse the response into HistoricalDataResponseDto using ObjectMapper
                return objectMapper.readValue(responseBody, HistoricalDataResponseDto.class);
            } else {
                // Handle no response body case
                HistoricalDataResponseDto errorResponse = new HistoricalDataResponseDto();
                errorResponse.setStatus(false);
                errorResponse.setMessage("No response body");
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response in case of an exception
            HistoricalDataResponseDto errorResponse = new HistoricalDataResponseDto();
            errorResponse.setStatus(false);
            errorResponse.setMessage("Error occurred: " + e.getMessage());
            return errorResponse;
        }
    }

    public HistoricalDataIOResponseDto getIOData(String exchange, String symboltoken, String interval, LocalDateTime fromdate,
                                                 LocalDateTime todate, String token, String privateKey) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // Format the dates into the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fromDateFormatted = fromdate.format(formatter);
        String toDateFormatted = todate.format(formatter);

        // Create the request body
        String requestBody = "{\n" +
                "   \"exchange\": \"" + exchange + "\",\n" +
                "   \"symboltoken\": \"" + symboltoken + "\",\n" +
                "   \"interval\": \"" + interval + "\",\n" +
                "   \"fromdate\": \"" + fromDateFormatted + "\",\n" +
                "   \"todate\": \"" + toDateFormatted + "\"\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        // Build the request
        Request request = new Request.Builder()
                .url(GET_IO_DATA_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + token)  // Add Authorization token
                .addHeader("X-PrivateKey", privateKey)  // Use the provided privateKey
                .addHeader("Accept", "application/json")
                .addHeader("X-SourceID", "WEB")
                .addHeader("X-ClientLocalIP", networkInfoService.getLocalIPAddress())  // Get Client Local IP
                .addHeader("X-ClientPublicIP", networkInfoService.getPublicIPAddress()) // Get Client Public IP
                .addHeader("X-MACAddress", networkInfoService.getMACAddress())         // Get MAC Address
                .addHeader("X-UserType", "USER")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();

                // Parse the response into HistoricalDataResponseDto using ObjectMapper
                return objectMapper.readValue(responseBody, HistoricalDataIOResponseDto.class);
            } else {
                // Handle no response body case
                HistoricalDataIOResponseDto errorResponse = new HistoricalDataIOResponseDto();
                errorResponse.setStatus(false);
                errorResponse.setMessage("No response body");
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response in case of an exception
            HistoricalDataIOResponseDto errorResponse = new HistoricalDataIOResponseDto();
            errorResponse.setStatus(false);
            errorResponse.setMessage("Error occurred: " + e.getMessage());
            return errorResponse;
        }
    }
}