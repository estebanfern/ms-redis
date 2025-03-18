package com.esteban.ms.cuentas.dto.mapper;

import com.esteban.ms.common.entity.Cliente;
import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.cuentas.dto.out.ReporteCuentaOut;
import com.esteban.ms.cuentas.dto.out.ReporteOut;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MovimientoMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReporteMapper {

    @Autowired
    protected MovimientoMapper movimientoMapper;

    public ReporteOut toOutput(Cliente cliente, List<Cuenta> cuentas) {
        List<ReporteCuentaOut> reportes = cuentas.stream().map(cuenta -> new ReporteCuentaOut(
            cuenta.getNumeroCuenta(),
            cuenta.getTipoCuenta(),
            cuenta.getSaldo(),
            // Se mapean los movimientos usando el mapper inyectado
            movimientoMapper.toOutput(cuenta.getMovimientos())
        )).toList();
        return new ReporteOut(
            cliente.getNombre(),
            cliente.getIdentificacion(),
            cliente.getDireccion(),
            cliente.getTelefono(),
            reportes
        );
    }
}


