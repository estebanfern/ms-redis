package com.esteban.ms.cuentas.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CrearCuentaIn extends CuentaIn {
    @NotNull
    private Long clienteId;
}
