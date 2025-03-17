package com.esteban.ms.clientes.dto.input;

import com.esteban.ms.common.entity.Genero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearClienteInput {
    @NotBlank
    private String nombre;
    @NotNull
    private Genero genero;
    @NotNull
    @Min(18)
    private Integer edad;
    @NotBlank
    private String identificacion;
    @NotBlank
    private String direccion;
    @NotBlank
    private String telefono;
    @NotBlank
    private String contrasena;
}
