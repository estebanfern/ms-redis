package com.esteban.ms.cuentas.dto.mapper;

import com.esteban.ms.common.entity.Cliente;
import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.cuentas.dto.out.ReporteCuentaOut;
import com.esteban.ms.cuentas.dto.out.ReporteOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    default ReporteOut toOutput(Cliente cliente, List<Cuenta> cuentas) {
        List<ReporteCuentaOut> reportes = toOutput(cuentas);
        return new ReporteOut(cliente.getNombre(), cliente.getIdentificacion(), cliente.getDireccion(), cliente.getTelefono(), reportes);
    }

    default List<ReporteCuentaOut> toOutput(List<Cuenta> cuentas) {
        return cuentas.stream().map(
            cuenta -> new ReporteCuentaOut(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta(), cuenta.getSaldo(), cuenta.getMovimientos())
        ).toList();
    }

}
