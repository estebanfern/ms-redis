package com.esteban.ms.cuentas.controller;

import com.esteban.ms.common.dto.MSResponse;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearMovimientoIn;
import com.esteban.ms.cuentas.dto.out.MovimientoOut;
import com.esteban.ms.cuentas.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    private final MovimientoService movimientoService;

    public MovimientosController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public MSResponse<MovimientoOut> generarMovimiento(@RequestBody @Valid CrearMovimientoIn input) throws MSException {
        return MSResponse.result(movimientoService.crearMovimiento(input));
    }

}
