package com.esteban.ms.cuentas.service;

import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearMovimientoIn;
import com.esteban.ms.cuentas.dto.out.MovimientoOut;

public interface MovimientoService {
    MovimientoOut crearMovimiento(CrearMovimientoIn movimiento) throws MSException;
}
