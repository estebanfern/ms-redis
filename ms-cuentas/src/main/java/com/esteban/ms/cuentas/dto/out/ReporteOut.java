package com.esteban.ms.cuentas.dto.out;

import java.util.List;

public record ReporteOut(
        String nombre,
        String identificacion,
        String direccion,
        String telefono,
        List<ReporteCuentaOut> cuentas
) {
}
