package com.esteban.ms.clientes.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EditarClienteInput extends CrearClienteInput {
    @NotNull
    private Boolean active;
}
