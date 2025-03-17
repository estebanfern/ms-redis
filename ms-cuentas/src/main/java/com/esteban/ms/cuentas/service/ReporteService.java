package com.esteban.ms.cuentas.service;

import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.out.ReporteOut;

import java.time.OffsetDateTime;

public interface ReporteService {
    ReporteOut getReporte(Long id, OffsetDateTime begin, OffsetDateTime end) throws MSException;
}
