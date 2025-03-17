package com.esteban.ms.common.config;

import com.esteban.ms.common.listener.AbstractListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.List;

@Configuration
public class ListenerConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;
    private final List<AbstractListener<?>> listeners;

    public ListenerConfiguration(RedisConnectionFactory redisConnectionFactory, List<AbstractListener<?>> listeners) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.listeners = listeners;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        for (AbstractListener<?> listener : listeners) {
            container.addMessageListener(listener, new ChannelTopic(listener.getChannel()));
        }
        return container;
    }

}
