package com.example.apigatewaypoc.controller;

import com.amazonaws.services.apigateway.model.ImportDocumentationPartsResult;
import com.amazonaws.services.apigateway.model.ImportRestApiResult;
import com.amazonaws.services.apigatewayv2.model.ImportApiRequest;
import com.amazonaws.services.apigatewayv2.model.ImportApiResult;
import com.example.apigatewaypoc.GatewayRequest;
import com.example.apigatewaypoc.service.GatewayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class GatewayController {

    private final GatewayService gatewayService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImportApiResult> createGateway(@RequestBody GatewayRequest gatewayRequest){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gatewayService.createApiGatewayV2(gatewayRequest));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImportDocumentationPartsResult> updateGateway(@RequestBody GatewayRequest gatewayRequest){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gatewayService.updateApi(gatewayRequest));
    }
}
