package com.esteban.ms.cuentas.repository;

import com.esteban.ms.common.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
