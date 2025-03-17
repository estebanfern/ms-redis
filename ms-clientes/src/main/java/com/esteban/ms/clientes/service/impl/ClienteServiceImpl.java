package com.esteban.ms.clientes.service.impl;

import com.esteban.ms.clientes.dto.in.ClienteSearchType;
import com.esteban.ms.clientes.dto.in.CrearClienteIn;
import com.esteban.ms.clientes.dto.in.EditarClienteIn;
import com.esteban.ms.clientes.dto.mapper.ClienteMapper;
import com.esteban.ms.clientes.dto.out.ClienteOut;
import com.esteban.ms.clientes.repository.ClienteRepository;
import com.esteban.ms.clientes.service.ClienteService;
import com.esteban.ms.clientes.service.PasswordEncoder;
import com.esteban.ms.common.entity.Cliente;
import com.esteban.ms.common.exception.ErrorCode;
import com.esteban.ms.common.exception.Location;
import com.esteban.ms.common.exception.MSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClienteOut crearCliente(CrearClienteIn input) throws MSException {
        try {
            log.info("Creating client {}", input);
            Cliente cliente = clienteMapper.toEntity(input);
            cliente.setActive(Boolean.TRUE);
            cliente.setContrasena(
                    passwordEncoder.hashPassword(cliente.getContrasena())
            );
            cliente = clienteRepository.save(cliente);
            ClienteOut output = clienteMapper.toOutput(cliente);
            log.debug("Client created {}", output);
            return output;
        } catch (DataIntegrityViolationException e) {
            String msg = String.format("Identification %s already exists", input.getIdentificacion());
            log.warn(msg);
            throw new MSException(msg, Location.CLIENTES, ErrorCode.A002, e);
        }
    }

    @Override
    public ClienteOut editarCliente(Long id, EditarClienteIn input) throws MSException {
        try {
            log.info("Editing client with id {} -> {}", id, input);
            Cliente cliente = findById(id);
            clienteMapper.updateFromDTO(input, cliente);
            clienteRepository.save(cliente);
            log.debug("Client edited {}", cliente);
            return clienteMapper.toOutput(cliente);
        } catch (DataIntegrityViolationException e) {
            String msg = String.format("Identification %s already exists", input.getIdentificacion());
            log.warn(msg);
            throw new MSException(msg, Location.CLIENTES, ErrorCode.A002, e);
        }
    }

    @Override
    public ClienteOut obtenerCliente(String id, ClienteSearchType type) throws MSException {
        log.info("Finding client with {} {}", type, id);
        try {
            return switch (type) {
                case ID -> clienteMapper.toOutput(findById(Long.parseLong(id)));
                case IDENTIFICACION -> clienteMapper.toOutput(clienteRepository.findClienteByIdentificacion(id).orElseThrow(
                    () -> {
                        log.warn("Client with identificacion {} not found", id);
                        return new MSException(
                            Location.CLIENTES,
                            ErrorCode.A003
                        );
                    }
                ));
            };
        } catch (NumberFormatException e) {
            log.warn("Invalid id {}", id);
            throw new MSException(
                Location.CLIENTES,
                ErrorCode.A001
            );
        }
    }

    private Cliente findById(Long id) throws MSException {
        log.info("Finding client with id {}", id);
        return clienteRepository.findById(id).orElseThrow(
            () -> {
                log.warn("Client with id {} not found", id);
                return new MSException(
                    Location.CLIENTES,
                    ErrorCode.A003
                );
            }
        );
    }

    @Override
    public void eliminarCliente(Long id) throws MSException {
        log.info("Deleting client with id {}", id);
        if (!clienteRepository.existsById(id)) {
            log.warn("Client with id {} not found", id);
            throw new MSException(
                Location.CLIENTES,
                ErrorCode.A003
            );
        }
        clienteRepository.deleteById(id);
    }

}
