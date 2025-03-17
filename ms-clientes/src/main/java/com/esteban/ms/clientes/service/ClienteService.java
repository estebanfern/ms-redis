package com.esteban.ms.clientes.service;

import com.esteban.ms.clientes.dto.in.ClienteSearchType;
import com.esteban.ms.clientes.dto.in.CrearClienteIn;
import com.esteban.ms.clientes.dto.in.EditarClienteIn;
import com.esteban.ms.clientes.dto.out.ClienteOut;
import com.esteban.ms.common.exception.MSException;

public interface ClienteService {
    ClienteOut crearCliente(CrearClienteIn input) throws MSException;
    ClienteOut editarCliente(Long id, EditarClienteIn input) throws MSException;
    ClienteOut obtenerCliente(String id, ClienteSearchType type) throws MSException;
    void eliminarCliente(Long id) throws MSException;
}
