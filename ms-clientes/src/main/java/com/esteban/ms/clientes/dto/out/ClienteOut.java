package com.esteban.ms.clientes.dto.out;

import com.esteban.ms.common.entity.Genero;
import lombok.Data;

@Data
public class ClienteOut {
    private Long id;
    private String nombre;
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Boolean active;
}
