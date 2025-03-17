package com.esteban.ms.clientes.dto.mapper;

import com.esteban.ms.clientes.dto.in.CrearClienteIn;
import com.esteban.ms.clientes.dto.in.EditarClienteIn;
import com.esteban.ms.clientes.dto.out.ClienteOut;
import com.esteban.ms.common.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {
    Cliente toEntity(CrearClienteIn input);
    ClienteOut toOutput(Cliente entity);
    void updateFromDTO(EditarClienteIn dto, @MappingTarget Cliente cliente);
}
