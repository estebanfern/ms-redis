package com.esteban.ms.clientes.repository;

import com.esteban.ms.common.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findClienteByIdentificacion(String identificacion);
}
