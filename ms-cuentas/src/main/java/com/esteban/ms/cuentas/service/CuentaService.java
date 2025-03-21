package com.esteban.ms.cuentas.service;

import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearCuentaIn;
import com.esteban.ms.cuentas.dto.in.EditarCuentaIn;
import com.esteban.ms.cuentas.dto.out.CuentaOut;

import java.util.List;

public interface CuentaService {
    CuentaOut crearCuenta(CrearCuentaIn input) throws MSException;
    CuentaOut editarCuenta(EditarCuentaIn input) throws MSException;
    CuentaOut obtenerCuenta(String id) throws MSException;
    void eliminarCuenta(String id) throws MSException;
    Cuenta findById(String id) throws MSException;
    void save(Cuenta cuenta)  throws MSException;
    List<Cuenta> findByClienteId(Long clienteId) throws MSException;
}
