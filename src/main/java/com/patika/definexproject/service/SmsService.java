package com.patika.definexproject.service;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Service
public class SmsService {

    @Value("${nexmo.api.key}")
    private String apiKey;

    @Value("${nexmo.api.secret}")
    private String apiSecret;

    public void sendSms(String phoneNumber, String message) throws IOException {
        try {
            Properties properties = readPropertiesFile("src/main/resources/application.properties");
            apiKey = properties.getProperty("nexmo.api.key");
            apiSecret = properties.getProperty("nexmo.api.secret");

            NexmoClient client = new NexmoClient.Builder()
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    .build();
            phoneNumber = "90"+phoneNumber;
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(new TextMessage(
                    "Spring Boot SMS", phoneNumber, message));

            System.out.println(response);
        } catch (NexmoClientException e) {
            e.printStackTrace();
        }
    }

    private Properties readPropertiesFile(String fileName) throws IOException {
        Properties properties = new Properties();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        properties.load(reader);
        reader.close();
        return properties;
    }
}