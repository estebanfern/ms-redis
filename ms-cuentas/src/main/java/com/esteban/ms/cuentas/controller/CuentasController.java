package com.esteban.ms.cuentas.controller;

import com.esteban.ms.common.dto.MSResponse;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearCuentaIn;
import com.esteban.ms.cuentas.dto.in.EditarCuentaIn;
import com.esteban.ms.cuentas.dto.out.CuentaOut;
import com.esteban.ms.cuentas.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentasController {

    private final CuentaService cuentaService;

    public CuentasController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<CuentaOut> get(@PathVariable("id") String id) throws MSException {
        return MSResponse.result(cuentaService.obtenerCuenta(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<CuentaOut> crearCuenta(@RequestBody @Valid CrearCuentaIn input) throws MSException {
        return MSResponse.result(cuentaService.crearCuenta(input));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<CuentaOut> editarCuenta(@RequestBody @Valid EditarCuentaIn input) throws MSException {
        return MSResponse.result(cuentaService.editarCuenta(input));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MSResponse<String> eliminarCuenta(@PathVariable("id") String id) throws MSException {
        cuentaService.eliminarCuenta(id);
        return MSResponse.result("OK");
    }

}
