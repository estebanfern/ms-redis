package com.esteban.ms.common.listener;

import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.common.model.Task;
import org.springframework.data.redis.connection.MessageListener;

public interface Listener<T extends Task<?, ?>> extends MessageListener {
    T process(T task) throws MSException;
}
