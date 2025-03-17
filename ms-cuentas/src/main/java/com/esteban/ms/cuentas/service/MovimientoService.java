package com.esteban.ms.cuentas.service;

import com.esteban.ms.common.entity.Movimiento;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearMovimientoIn;
import com.esteban.ms.cuentas.dto.out.MovimientoOut;

import java.time.OffsetDateTime;
import java.util.List;

public interface MovimientoService {
    MovimientoOut crearMovimiento(CrearMovimientoIn movimiento) throws MSException;
    List<Movimiento> findByCuentaIdAndDateBetween(String numeroCuenta, OffsetDateTime begin, OffsetDateTime end);
}
