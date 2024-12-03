package org.ormi.priv.tfa.orderflow.product.registry.aggregate.repository.model;

/**
 * Product registered event entity.
 */
public class ProductRegisteredEventEntity extends ProductRegistryEventEntity {
  /** The event type. */
  static final String EVENT_TYPE = "ProductRegistered";

  /**
   * Payload for the event.
   */
  public static record Payload() {
    /**
     * The id of the product.
     */
    static String productId;
    /**
     * The name of the product.
     */
    static String name;
    /**
     * The description of the product.
     */
    static String productDescription;
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

  /**
   * Get the event type.
   * 
   * @return The event type.
   */
  @Override
  public String getEventType() {
    return EVENT_TYPE;
  }
}
