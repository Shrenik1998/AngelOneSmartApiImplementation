package com.scaler.angelonesmartapidemo.Service;

import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

@Service
public class NetworkInfoService {

    // Method to get the local IP address
    public String getLocalIPAddress() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }

    // Method to get the public IP address
    public String getPublicIPAddress() throws Exception {
        URL url = new URL("http://checkip.amazonaws.com");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        return br.readLine();  // Returns the public IP as a string
    }

    // Method to get the MAC address
    public String getMACAddress() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(localHost);
        byte[] macBytes = network.getHardwareAddress();

        StringBuilder macAddress = new StringBuilder();
        for (int i = 0; i < macBytes.length; i++) {
            macAddress.append(String.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : ""));
        }
        return macAddress.toString();
    }
}
