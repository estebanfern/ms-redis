package com.esteban.ms.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Location {

    CLIENTES("ms-clientes"),
    CUENTAS("ms-cuentas"),
    COMMON("ms-common"),
    UNKNOWN("ms-unknown"),
    ;

    private final String microserviceName;

    public static Location getByMicroserviceName(String microserviceName) {
        for (Location location : Location.values()) {
            if (location.getMicroserviceName().equals(microserviceName)) {
                return location;
            }
        }
        return UNKNOWN;
    }

}
