package com.roomManagement.controller;

import com.roomManagement.dto.SummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("roomManagement")
public class SummaryController {
    @Autowired
    private final RestTemplate restTemplate;

    public SummaryController(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Sistema mostra os itens selecionados, incluindo datas, tipos de quartos e opcionais, e também o
    //valor total.
    @GetMapping("/summary/userId")
    public ResponseEntity<SummaryResponse> summary(@PathVariable final Long userId) {
        restTemplate.getForObject("localhost:x", String.class);

        final SummaryResponse summaryResponse = new SummaryResponse();

        return ResponseEntity.ok(summaryResponse);
    }
}
