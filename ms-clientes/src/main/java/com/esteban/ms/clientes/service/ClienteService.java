package com.esteban.ms.clientes.service;

import com.esteban.ms.clientes.dto.input.ClienteSearchType;
import com.esteban.ms.clientes.dto.input.CrearClienteInput;
import com.esteban.ms.clientes.dto.input.EditarClienteInput;
import com.esteban.ms.clientes.dto.output.ClienteOutput;
import com.esteban.ms.common.exception.MSException;

public interface ClienteService {
    ClienteOutput crearCliente(CrearClienteInput input) throws MSException;
    ClienteOutput editarCliente(Long id, EditarClienteInput input) throws MSException;
    ClienteOutput obtenerCliente(String id, ClienteSearchType type) throws MSException;
    void eliminarCliente(Long id) throws MSException;
}
