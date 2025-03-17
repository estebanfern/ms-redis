package com.esteban.ms.cuentas.service.impl;

import com.esteban.ms.common.constant.Channel;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.common.model.GetClienteTask;
import com.esteban.ms.common.service.TaskService;
import com.esteban.ms.cuentas.dto.mapper.ReporteMapper;
import com.esteban.ms.cuentas.dto.out.ReporteOut;
import com.esteban.ms.cuentas.service.CuentaService;
import com.esteban.ms.cuentas.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class ReporteServiceImpl implements ReporteService {

    private final CuentaService cuentaService;
    private final TaskService taskService;
    private final ReporteMapper reporteMapper;

    public ReporteServiceImpl(CuentaService cuentaService, TaskService taskService, ReporteMapper reporteMapper) {
        this.cuentaService = cuentaService;
        this.taskService = taskService;
        this.reporteMapper = reporteMapper;
    }

    @Override
    public ReporteOut getReporte(Long id, OffsetDateTime begin, OffsetDateTime end) throws MSException {
        log.info("Getting report for id: {}, begin: {}, end: {}", id, begin, end);
        GetClienteTask task = new GetClienteTask();
        task.setData(id);
        task = taskService.sendTaskAndWait(Channel.GET_CLIENTE, task, GetClienteTask.class, Duration.of(120, ChronoUnit.SECONDS));
        log.info("{}", task);
        return null;
    }

}
