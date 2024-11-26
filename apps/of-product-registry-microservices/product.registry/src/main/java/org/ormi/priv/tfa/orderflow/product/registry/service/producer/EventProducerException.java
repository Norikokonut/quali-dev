package org.ormi.priv.tfa.orderflow.product.registry.service.producer;

public class EventProducerException extends RuntimeException {
    public EventProducerException(String message) {
        super(message);
    }

    public EventProducerException(String message, Throwable cause) {
        super(message, cause);
    }
}
