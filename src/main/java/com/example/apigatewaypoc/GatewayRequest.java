package com.example.apigatewaypoc;

import lombok.Data;

@Data
public class GatewayRequest {
    private String specName;
    private String region;
    private String accessKey;
    private String secretKey;
    private String body;
    private String bodyUrl;
    private String specId;
    private String basePath;
}
