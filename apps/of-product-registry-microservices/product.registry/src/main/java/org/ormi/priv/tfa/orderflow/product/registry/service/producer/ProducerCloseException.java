package org.ormi.priv.tfa.orderflow.product.registry.service.producer;

public class ProducerCloseException extends EventProducerException {
    public ProducerCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
