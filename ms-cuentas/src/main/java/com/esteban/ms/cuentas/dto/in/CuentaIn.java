package com.esteban.ms.cuentas.dto.in;

import com.esteban.ms.common.entity.TipoCuenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class CuentaIn {
    @NotBlank
    private String numeroCuenta;
    @NotNull
    private TipoCuenta tipoCuenta;
}
