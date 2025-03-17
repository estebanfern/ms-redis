package com.esteban.ms.cuentas.dto.out;

import com.esteban.ms.common.entity.TipoCuenta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaOut {
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private BigDecimal saldo;
    private Boolean active;
}
