package com.esteban.ms.clientes.controller;

import com.esteban.ms.common.dto.MSResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping
    public ResponseEntity<MSResponse<Object>> get() {
        return ResponseEntity.ok(new MSResponse<>("clientes"));
    }

}
