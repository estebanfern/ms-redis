package com.esteban.ms.common.listener;

import com.esteban.ms.common.dto.Error;
import com.esteban.ms.common.exception.ErrorCode;
import com.esteban.ms.common.exception.Location;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.common.model.Task;
import com.esteban.ms.common.util.TaskUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractListener<T extends Task<?, ?>> implements Listener<T> {

    @Value("${spring.application.name:ms-common}")
    private String applicationName;
    protected final ObjectMapper mapper;

    protected AbstractListener() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private void saveResult(T task) {
        try {
            String resultJson = mapper.writeValueAsString(task);
            redisTemplate.opsForValue().set(
                TaskUtil.getTaskResultKey(getChannel(), task.getId()),
                resultJson,
                120,
                TimeUnit.SECONDS
            );
        } catch (JsonProcessingException e) {
            log.error("FATAL! - error serializing JSON: {}", e.getMessage(), e);
        }
    }

    @Override
    public final void onMessage(@NotNull Message message, byte[] pattern) {
        T task = null;
        try {
            task = mapper.readValue(message.getBody(), getTaskClass());
            T result = process(task);
            saveResult(result);
        } catch (Throwable e) {
            log.error("Error processing message: {}", e.getMessage(), e);
            if (task != null) {
                MSException ex;
                Error error = new Error();
                if (e instanceof MSException) {
                    ex = (MSException) e;
                } else {
                    ex = new MSException(
                        Location.COMMON,
                        ErrorCode.U001,
                        e
                    );
                }
                error.setMessage(ex.getMessage());
                error.setLocation(ex.getLocation().name());
                error.setCode(ex.getErrorCode().name());
                task.setError(error);
                saveResult(task);
            }
        }
    }

    @Override
    public T process(T task) throws MSException {
        log.debug("Processing task: {}", task);
        return task;
    }

    protected abstract Class<T> getTaskClass();
    public abstract String getChannel();
}

