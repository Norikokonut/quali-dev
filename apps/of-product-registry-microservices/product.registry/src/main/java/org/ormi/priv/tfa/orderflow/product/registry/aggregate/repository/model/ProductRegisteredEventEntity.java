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
  public static class Payload {
    /**
     * The id of the product.
     */
    public String productId;
    /**
     * The name of the product.
     */
    public String name;
    /**
     * The description of the product.
     */
    public String productDescription;
  }

  /**
   * The payload for the event.
   */
  public Payload payload;


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
