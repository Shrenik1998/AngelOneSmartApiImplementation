package com.scaler.angelonesmartapidemo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.angelonesmartapidemo.dtos.LoginResponseDTO;
import com.scaler.angelonesmartapidemo.dtos.UserInfoResponseDto;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartApiUserService {

    @Autowired
    private NetworkInfoService networkInfoService;

    private static final String LOGIN_URL = "https://apiconnect.angelone.in/rest/auth/angelbroking/user/v1/loginByPassword";
    private static final String GET_USER_INFO_URL = "https://apiconnect.angelone.in/rest/secure/angelbroking/user/v1/getProfile";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public LoginResponseDTO login(String clientCode, String password, String totp, String privateKey) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        String requestBody = "{\n" +
                "  \"clientcode\":\"" + clientCode + "\",\n" +
                "  \"password\":\"" + password + "\",\n" +
                "  \"totp\":\"" + totp + "\"\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        Request request = new Request.Builder()
                .url(LOGIN_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("X-UserType", "USER")
                .addHeader("X-SourceID", "WEB")
                .addHeader("X-ClientLocalIP", networkInfoService.getLocalIPAddress()) // Replace with CLIENT_LOCAL_IP
                .addHeader("X-ClientPublicIP", networkInfoService.getPublicIPAddress()) // Replace with CLIENT_PUBLIC_IP
                .addHeader("X-MACAddress", networkInfoService.getMACAddress()) // Replace with MAC_ADDRESS
                .addHeader("X-PrivateKey", privateKey) // Use the privateKey from the DTO
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();
                // Parse the response body into LoginResponseDTO using ObjectMapper
                return objectMapper.readValue(responseBody, LoginResponseDTO.class);
            } else {
                // Return an error response if the body is null
                LoginResponseDTO errorResponse = new LoginResponseDTO();
                errorResponse.setStatus(false);
                errorResponse.setMessage("No response body");
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response in case of an exception
            LoginResponseDTO errorResponse = new LoginResponseDTO();
            errorResponse.setStatus(false);
            errorResponse.setMessage("Error occurred: " + e.getMessage());
            return errorResponse;
        }
    }

    public UserInfoResponseDto getUserInfo(String token,String privateKey) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Build the GET request with headers
        Request request = new Request.Builder()
                .url(GET_USER_INFO_URL)
                .get()
                .addHeader("Authorization", "Bearer " + token)  // Set the Authorization token
                .addHeader("Accept", "application/json")
                .addHeader("X-UserType", "USER")
                .addHeader("X-SourceID", "WEB")
                .addHeader("X-ClientLocalIP", networkInfoService.getLocalIPAddress())
                .addHeader("X-ClientPublicIP", networkInfoService.getPublicIPAddress())
                .addHeader("X-MACAddress", networkInfoService.getMACAddress())
                .addHeader("X-PrivateKey", privateKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String responseBody = response.body().string();

                // Parse the response body into UserInfoResponseDto
                return objectMapper.readValue(responseBody, UserInfoResponseDto.class);
            } else {
                // Handle no response body case
                UserInfoResponseDto errorResponse = new UserInfoResponseDto();
                errorResponse.setStatus(false);
                errorResponse.setMessage("No response body");
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response in case of an exception
            UserInfoResponseDto errorResponse = new UserInfoResponseDto();
            errorResponse.setStatus(false);
            errorResponse.setMessage("Error occurred: " + e.getMessage());
            return errorResponse;
        }
    }
}
