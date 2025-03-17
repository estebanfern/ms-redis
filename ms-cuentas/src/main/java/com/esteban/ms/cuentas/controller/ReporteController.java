package com.esteban.ms.cuentas.controller;

import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.cuentas.dto.out.ReporteOut;
import com.esteban.ms.cuentas.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ReporteOut getReporte(@RequestParam Long cliente,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime inicio,
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam OffsetDateTime fin) throws MSException {
        return reporteService.getReporte(cliente, inicio, fin);
    }

}
