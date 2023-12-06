package com.example.apigatewaypoc.service;

import com.example.apigatewaypoc.GatewayRequest;
import com.example.apigatewaypoc.config.GatewayConfig;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;
import software.amazon.awssdk.services.apigateway.model.ImportRestApiRequest;
import software.amazon.awssdk.services.apigateway.model.ImportRestApiResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GatewayService {

    public void createApiGateway(GatewayRequest gatewayRequest){
        ApiGatewayClient apiGatewayClient = GatewayConfig.getApiGatewayClient(gatewayRequest.getAccessKey(), gatewayRequest.getSecretKey(), gatewayRequest.getRegion());

        Map<String, String> parameters= new HashMap<>();
        parameters.put("endpointConfigurationTypes", "REGIONAL");
        ImportRestApiRequest request = ImportRestApiRequest.builder()
                .failOnWarnings(false)
                .parameters(parameters)
                .body(SdkBytes.fromUtf8String(gatewayRequest.getBody()))
                .build();

        ImportRestApiResponse response = apiGatewayClient.importRestApi(request);
        System.out.println("The id of the new api is "+response.id());
        apiGatewayClient.close();

    }
}
