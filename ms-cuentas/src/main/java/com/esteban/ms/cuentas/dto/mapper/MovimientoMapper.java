package com.esteban.ms.cuentas.dto.mapper;

import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.common.entity.Movimiento;
import com.esteban.ms.cuentas.dto.in.CrearMovimientoIn;
import com.esteban.ms.cuentas.dto.out.MovimientoOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mapping(source = "numeroCuenta", target = "cuenta", qualifiedByName = "cuentaFromNumero")
    Movimiento toEntity(CrearMovimientoIn input);
    MovimientoOut toOutput(Movimiento movimiento);

    @Named("cuentaFromNumero")
    default Cuenta cuentaFromNumero(String numeroCuenta) {
        if (numeroCuenta == null) {
            return null;
        }
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(numeroCuenta);
        return cuenta;
    }

}
