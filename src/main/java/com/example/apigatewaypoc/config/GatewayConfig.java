package com.example.apigatewaypoc.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.AmazonApiGatewayClientBuilder;


public class GatewayConfig {

    public static AmazonApiGateway getApiGatewayClient(String awsAccessKey, String awsSecretKey, String region){
        return AmazonApiGatewayClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .withRegion(region).build();

    }



}
