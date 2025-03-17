package com.esteban.ms.cuentas.dto.out;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class MovimientoOut {
    private OffsetDateTime fecha;
    private BigDecimal saldoInicial;
    private BigDecimal monto;
    private String descripcion;
}
