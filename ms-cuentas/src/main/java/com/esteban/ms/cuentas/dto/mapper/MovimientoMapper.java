package com.esteban.ms.cuentas.dto.mapper;

import com.esteban.ms.common.entity.Cuenta;
import com.esteban.ms.common.entity.Movimiento;
import com.esteban.ms.cuentas.dto.in.CrearMovimientoIn;
import com.esteban.ms.cuentas.dto.out.MovimientoOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovimientoMapper {

    @Mapping(source = "numeroCuenta", target = "cuenta", qualifiedByName = "cuentaFromNumero")
    Movimiento toEntity(CrearMovimientoIn input);

    MovimientoOut toOutput(Movimiento movimiento);

    // Método auxiliar para convertir el número de cuenta a un objeto Cuenta
    @Named("cuentaFromNumero")
    default Cuenta cuentaFromNumero(String numeroCuenta) {
        if (numeroCuenta == null) {
            return null;
        }
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(numeroCuenta);
        return cuenta;
    }

    // Método para mapear una lista de movimientos (MapStruct puede generarlo automáticamente si se declara)
    default List<MovimientoOut> toOutput(List<Movimiento> movimientos) {
        return movimientos.stream()
            .map(this::toOutput)
            .toList();
    }

}
