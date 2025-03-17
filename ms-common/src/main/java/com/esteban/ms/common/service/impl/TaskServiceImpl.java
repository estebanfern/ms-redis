package com.esteban.ms.common.service.impl;

import com.esteban.ms.common.exception.ErrorCode;
import com.esteban.ms.common.exception.Location;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.common.model.Task;
import com.esteban.ms.common.service.TaskService;
import com.esteban.ms.common.util.TaskUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Value("${spring.application.name:ms-common}")
    private String applicationName;
    private static final long pollInterval = 100;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper mapper;

    public TaskServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T extends Task<?, ?>> void sendTask(String channel, T task, Class<T> taskClass) throws MSException {
        try {
            redisTemplate.convertAndSend(channel, task);
            log.debug("Task send {} to channel {}", task, channel);
        } catch (Throwable e) {
            log.error("Error sending task", e);
            throw new MSException(
                Location.getByMicroserviceName(applicationName),
                ErrorCode.U001
            );
        }
    }

    @Override
    public <T extends Task<?, ?>> T sendTaskAndWait(String channel, T task, Class<T> taskClass, Duration timeout) throws MSException {
        sendTask(channel, task, taskClass);
        String key = TaskUtil.getTaskResultKey(channel, task.getId());
        long startTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - startTime < timeout.toMillis()) {
                Object respuesta = redisTemplate.opsForValue().get(key);
                if (respuesta != null) {
                    T taskResult;
                    if (respuesta instanceof String) {
                        taskResult = mapper.readValue((String) respuesta, taskClass);
                    } else {
                        taskResult = taskClass.cast(respuesta);
                    }
                    if (Objects.nonNull(taskResult.getError())) {
                         throw MSException.from(taskResult.getError());
                    }
                    return taskResult;
                }
                Thread.sleep(pollInterval);
            }
        } catch (Throwable e) {
            log.error("Error waiting task result {}.", e.getMessage(), e);
        }
        throw new MSException(
            Location.getByMicroserviceName(applicationName),
            ErrorCode.A001
        );
    }

}
