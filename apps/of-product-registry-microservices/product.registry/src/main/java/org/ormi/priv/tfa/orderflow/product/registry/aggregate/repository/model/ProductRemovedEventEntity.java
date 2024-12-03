package org.ormi.priv.tfa.orderflow.product.registry.aggregate.repository.model;

/**
 * Product removed event entity.
 */
public class ProductRemovedEventEntity extends ProductRegistryEventEntity {
  /** The event type. */
  static final String EVENT_TYPE = "ProductRemoved";

  /**
   * Payload for the event.
   */
  public static record Payload() {
    /**
     * The id of the product.
     */
    static String productId;
  }

  /**
     * The payload for the event.
     */
    private Payload payload;

    /**
     * Getter for the payload.
     *
     * @return the payload
     */
    public Payload getPayload() {
        return payload;
    }

  @Override
  public String getEventType() {
    return EVENT_TYPE;
  }
  
}
