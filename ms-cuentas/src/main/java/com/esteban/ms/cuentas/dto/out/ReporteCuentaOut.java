package com.esteban.ms.cuentas.dto.out;

import com.esteban.ms.common.entity.Movimiento;
import com.esteban.ms.common.entity.TipoCuenta;

import java.math.BigDecimal;
import java.util.List;

public record ReporteCuentaOut(
        String numeroCuenta,
        TipoCuenta tipoCuenta,
        BigDecimal saldo,
        List<Movimiento> movimientos
) {
}
