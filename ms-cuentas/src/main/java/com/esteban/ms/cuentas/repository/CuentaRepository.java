package com.esteban.ms.cuentas.repository;

import com.esteban.ms.common.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
}
