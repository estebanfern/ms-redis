package com.esteban.ms.cuentas.repository;

import com.esteban.ms.common.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    List<Cuenta> findAllByCliente_Id(Long clienteId);
}
