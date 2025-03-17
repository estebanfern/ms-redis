package com.esteban.ms.cuentas.dto.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrearMovimientoIn {
    @NotNull
    private BigDecimal monto;
    @NotEmpty
    private String descripcion;
    @NotEmpty
    private String numeroCuenta;
}
