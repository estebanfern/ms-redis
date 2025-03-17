package com.esteban.ms.cuentas.repository;

import com.esteban.ms.common.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findAllByCuenta_NumeroCuentaAndFechaBetween(String numeroCuenta, OffsetDateTime begin, OffsetDateTime end);
}
