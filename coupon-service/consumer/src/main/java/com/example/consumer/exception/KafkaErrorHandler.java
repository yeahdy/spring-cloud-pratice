package com.example.consumer.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaErrorHandler implements KafkaListenerErrorHandler {
    //TODO. Kafka listner에서 에러가 발생하면 3개의 핸들러 중 어디로 가는거지?

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer,
                              Acknowledgment ack) {
        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer, ack);
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        log.error("validation error handler message={} exception groupId={} exception cause={}",
                message.getPayload(), exception.getGroupId(), exception.getRootCause());
        return message; //return 값이 topic에 write
    }
}
