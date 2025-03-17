package com.esteban.ms.common.service;

import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.common.model.Task;

import java.time.Duration;

public interface TaskService {

    <T extends Task<?, ?>> void sendTask(String channel, T task, Class<T> taskClass) throws MSException;
    <T extends Task<?, ?>> T sendTaskAndWait(String channel, T task, Class<T> taskClass, Duration timeout) throws MSException;

}
