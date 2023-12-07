package com.example.apigatewaypoc.service;

import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.model.*;
import com.amazonaws.services.apigatewayv2.AmazonApiGatewayV2;
import com.amazonaws.services.apigatewayv2.model.ImportApiRequest;
import com.amazonaws.services.apigatewayv2.model.ImportApiResult;
import com.amazonaws.services.apigatewayv2.model.ReimportApiRequest;
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
        System.out.println(importRestApiResult.toString());
        System.out.println("The id of the new api is "+importRestApiResult.getId());
        return importRestApiResult;
    }

    public ImportApiResult createApiGatewayV2(GatewayRequest gatewayRequest){
        AmazonApiGatewayV2 apiGatewayClientV2 = GatewayConfig.getApiGatewayClientV2(gatewayRequest.getAccessKey(), gatewayRequest.getSecretKey(), gatewayRequest.getRegion());
        ImportApiRequest importApiRequest = new ImportApiRequest();
        //importApiRequest.setBasepath(gatewayRequest.getBasePath());
        importApiRequest.setBody(gatewayRequest.getBody());
        importApiRequest.setFailOnWarnings(false);

        return apiGatewayClientV2.importApi(importApiRequest);
    }

    public ImportDocumentationPartsResult updateApi(GatewayRequest gatewayRequest){
        AmazonApiGateway apiGatewayClient = GatewayConfig.getApiGatewayClient(gatewayRequest.getAccessKey(), gatewayRequest.getSecretKey(), gatewayRequest.getRegion());
        ImportDocumentationPartsRequest importDocumentationPartsRequest = new ImportDocumentationPartsRequest();
        importDocumentationPartsRequest.setMode(PutMode.Overwrite);
        importDocumentationPartsRequest.setRestApiId(gatewayRequest.getSpecId());
        importDocumentationPartsRequest.setBody(ByteBuffer.wrap(gatewayRequest.getBody().getBytes()));
        importDocumentationPartsRequest.setFailOnWarnings(false);
        System.out.println("In the update methhod");
        ImportDocumentationPartsResult importDocumentationPartsResult = apiGatewayClient.importDocumentationParts(importDocumentationPartsRequest);
        System.out.println(importDocumentationPartsResult.toString());
        return importDocumentationPartsResult;
    }
}
