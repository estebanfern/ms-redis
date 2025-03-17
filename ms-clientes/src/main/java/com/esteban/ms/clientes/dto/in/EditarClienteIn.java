package com.esteban.ms.clientes.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EditarClienteIn extends CrearClienteIn {
    @NotNull
    private Boolean active;
}
