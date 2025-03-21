package com.esteban.ms.cuentas.service.impl;

import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.common.exception.ErrorCode;
import com.esteban.ms.common.exception.Location;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.in.CrearCuentaIn;
import com.esteban.ms.cuentas.dto.in.EditarCuentaIn;
import com.esteban.ms.cuentas.dto.mapper.CuentaMapper;
import com.esteban.ms.cuentas.dto.out.CuentaOut;
import com.esteban.ms.cuentas.repository.CuentaRepository;
import com.esteban.ms.cuentas.service.CuentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public CuentaOut crearCuenta(CrearCuentaIn input) throws MSException {
        if (cuentaRepository.existsById(input.getNumeroCuenta())) {
            log.warn("Account with number {} already exists", input.getNumeroCuenta());
            throw new MSException(
                    Location.CUENTAS,
                    ErrorCode.A004
            );
        }
        Cuenta cuenta = cuentaMapper.toEntity(input);
        cuenta.setSaldo(BigDecimal.valueOf(0));
        cuenta.setActive(true);
        save(cuenta);
        CuentaOut cuentaOut = cuentaMapper.toOutput(cuenta);
        log.debug("Account created: {}", cuentaOut);
        return cuentaOut;
    }

    @Override
    public CuentaOut editarCuenta(EditarCuentaIn input) throws MSException {
        log.info("Editing account {}", input);
        Cuenta cuenta = findById(input.getNumeroCuenta());
        cuentaMapper.updateFromDTO(input, cuenta);
        save(cuenta);
        log.debug("Account edited: {}", cuenta);
        return cuentaMapper.toOutput(cuenta);
    }

    @Override
    public void save(Cuenta cuenta) throws MSException {
        try {
            cuentaRepository.save(cuenta);
        } catch (DataIntegrityViolationException e) {
            log.warn("Cliente with id {} not found.", cuenta.getCliente().getId());
            throw new MSException(
                    Location.CUENTAS,
                    ErrorCode.A003
            );
        }
    }

    @Override
    public CuentaOut obtenerCuenta(String id) throws MSException {
        return cuentaMapper.toOutput(findById(id));
    }

    @Override
    public Cuenta findById(String id) throws MSException {
        return cuentaRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Account with number {} not found.", id);
                    return new MSException(
                        Location.CUENTAS,
                        ErrorCode.A005
                    );
                }
        );
    }

    @Override
    public void eliminarCuenta(String id) throws MSException {
        if (!cuentaRepository.existsById(id)) {
            log.warn("Account with number {} not found.", id);
            throw new MSException(
                Location.CUENTAS,
                ErrorCode.A005
            );
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId) throws MSException {
        return cuentaRepository.findAllByCliente_Id(clienteId);
    }

}
