package com.esteban.ms.clientes.controller;

import com.esteban.ms.clientes.dto.in.ClienteSearchType;
import com.esteban.ms.clientes.dto.in.CrearClienteIn;
import com.esteban.ms.clientes.dto.in.EditarClienteIn;
import com.esteban.ms.clientes.dto.out.ClienteOut;
import com.esteban.ms.clientes.service.ClienteService;
import com.esteban.ms.common.dto.MSResponse;
import com.esteban.ms.common.exception.MSException;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<ClienteOut> get(@RequestParam String id, @RequestParam ClienteSearchType type) throws MSException {
        return MSResponse.result(clienteService.obtenerCliente(id, type));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<ClienteOut> crearCliente(@RequestBody @Valid CrearClienteIn input) throws MSException {
        return MSResponse.result(clienteService.crearCliente(input));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<ClienteOut> editarCliente(@PathVariable Long id, @RequestBody @Valid EditarClienteIn input) throws MSException {
        return MSResponse.result(clienteService.editarCliente(id, input));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<String> eliminarCliente(@PathVariable Long id) throws MSException {
        clienteService.eliminarCliente(id);
        return MSResponse.result("OK");
    }

}
