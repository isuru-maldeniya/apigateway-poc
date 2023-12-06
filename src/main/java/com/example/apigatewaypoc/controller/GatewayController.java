package com.example.apigatewaypoc.controller;

import com.example.apigatewaypoc.GatewayRequest;
import com.example.apigatewaypoc.service.GatewayService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class GatewayController {

    private final GatewayService gatewayService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createGateway(@RequestBody GatewayRequest gatewayRequest){
        gatewayService.createApiGateway(gatewayRequest);
        return ResponseEntity.noContent().build();

    }
}
