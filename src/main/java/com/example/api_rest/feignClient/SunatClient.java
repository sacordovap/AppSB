package com.example.api_rest.feignClient;

import com.example.api_rest.dto.response.SunatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sunat-client", url = "https://api.decolecta.com/v1/sunat")
public interface SunatClient {

    @GetMapping("/ruc/full")
    SunatResponse getInfoSunat(@RequestParam("numero") String ruc,
                               @RequestHeader("Authorization") String token);
}