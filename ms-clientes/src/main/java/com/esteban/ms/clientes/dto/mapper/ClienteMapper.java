package com.esteban.ms.clientes.dto.mapper;

import com.esteban.ms.clientes.dto.input.CrearClienteInput;
import com.esteban.ms.clientes.dto.input.EditarClienteInput;
import com.esteban.ms.clientes.dto.output.ClienteOutput;
import com.esteban.ms.common.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {
    Cliente toEntity(CrearClienteInput input);
    ClienteOutput toOutput(Cliente entity);
    void updateFromDTO(EditarClienteInput dto, @MappingTarget Cliente cliente);
}
