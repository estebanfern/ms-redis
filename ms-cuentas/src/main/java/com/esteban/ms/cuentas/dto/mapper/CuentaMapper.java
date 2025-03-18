package com.esteban.ms.cuentas.dto.mapper;

import com.esteban.ms.common.entity.Cliente;
import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.cuentas.dto.in.CrearCuentaIn;
import com.esteban.ms.cuentas.dto.in.EditarCuentaIn;
import com.esteban.ms.cuentas.dto.out.CuentaOut;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuentaMapper {

    @Mapping(source = "clienteId", target = "cliente", qualifiedByName = "clienteFromId")
    Cuenta toEntity(CrearCuentaIn dto);

    @Named("clienteFromId")
    default Cliente clienteFromId(Long clienteId) {
        if (clienteId == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        return cliente;
    }

    CuentaOut toOutput(Cuenta cuenta);
    void updateFromDTO(EditarCuentaIn dto, @MappingTarget Cuenta cuenta);

}
