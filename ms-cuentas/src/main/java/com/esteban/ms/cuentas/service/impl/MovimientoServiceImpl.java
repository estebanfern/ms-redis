package com.esteban.ms.cuentas.service.impl;

import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.common.entity.Movimiento;
import com.esteban.ms.common.exception.ErrorCode;
import com.esteban.ms.common.exception.Location;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearMovimientoIn;
import com.esteban.ms.cuentas.dto.mapper.MovimientoMapper;
import com.esteban.ms.cuentas.dto.out.MovimientoOut;
import com.esteban.ms.cuentas.repository.MovimientoRepository;
import com.esteban.ms.cuentas.service.CuentaService;
import com.esteban.ms.cuentas.service.MovimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@Slf4j
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService;
    private final MovimientoMapper movimientoMapper;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaService cuentaService, MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaService = cuentaService;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public MovimientoOut crearMovimiento(CrearMovimientoIn input) throws MSException {
        Cuenta cuenta = cuentaService.findById(input.getNumeroCuenta());
        if (input.getMonto().compareTo(BigDecimal.valueOf(0)) < 0 && cuenta.getSaldo().compareTo(input.getMonto().abs()) < 0) {
            // Se intenta hacer un egreso de un monto mayor al disponible.
            throw new MSException(
                Location.CUENTAS,
                ErrorCode.A006
            );
        }
        Movimiento movimiento = movimientoMapper.toEntity(input);
        movimiento.setFecha(OffsetDateTime.now());
        movimiento.setSaldoInicial(cuenta.getSaldo());
        cuenta.setSaldo(cuenta.getSaldo().add(movimiento.getMonto()));
        movimiento.setCuenta(cuenta);
        cuentaService.save(cuenta);
        movimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toOutput(movimiento);
    }

}
