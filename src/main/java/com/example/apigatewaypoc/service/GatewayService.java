package com.example.apigatewaypoc.service;

import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.model.ImportRestApiRequest;
import com.amazonaws.services.apigateway.model.ImportRestApiResult;
import com.example.apigatewaypoc.GatewayRequest;
import com.example.apigatewaypoc.config.GatewayConfig;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

@Service
public class GatewayService {

    public ImportRestApiResult createApiGateway(GatewayRequest gatewayRequest){
        AmazonApiGateway apiGatewayClient = GatewayConfig.getApiGatewayClient(gatewayRequest.getAccessKey(), gatewayRequest.getSecretKey(), gatewayRequest.getRegion());

        Map<String, String> parameters= new HashMap<>();
        parameters.put("endpointConfigurationTypes", "REGIONAL");
        ImportRestApiRequest request = new ImportRestApiRequest();
        request.setFailOnWarnings(false);
        request.setParameters(parameters);
        request.setBody(ByteBuffer.wrap(gatewayRequest.getBody().getBytes()));

        ImportRestApiResult importRestApiResult = apiGatewayClient.importRestApi(request);
        System.out.println("The id of the new api is "+importRestApiResult.getId());
        return importRestApiResult;

    }
}
