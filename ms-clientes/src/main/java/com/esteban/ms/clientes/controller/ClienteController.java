package com.esteban.ms.clientes.controller;

import com.esteban.ms.clientes.dto.input.ClienteSearchType;
import com.esteban.ms.clientes.dto.input.CrearClienteInput;
import com.esteban.ms.clientes.dto.input.EditarClienteInput;
import com.esteban.ms.clientes.dto.output.ClienteOutput;
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
    public MSResponse<ClienteOutput> get(@RequestParam String id, @RequestParam ClienteSearchType type) throws MSException {
        return MSResponse.result(clienteService.obtenerCliente(id, type));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<ClienteOutput> crearCliente(@RequestBody @Valid CrearClienteInput input) throws MSException {
        return MSResponse.result(clienteService.crearCliente(input));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<ClienteOutput> editarCliente(@PathVariable Long id, @RequestBody @Valid EditarClienteInput input) throws MSException {
        return MSResponse.result(clienteService.editarCliente(id, input));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<String> eliminarCliente(@PathVariable Long id) throws MSException {
        clienteService.eliminarCliente(id);
        return MSResponse.result("OK");
    }

}
