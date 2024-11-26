package org.ormi.priv.tfa.orderflow.product.registry.aggregate.repository.model;

public class ProductUpdatedEventEntity extends ProductRegistryEventEntity {
  static final String EVENT_TYPE = "ProductUpdated";

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
  public Payload payload;


  @Override
  public String getEventType() {
    return EVENT_TYPE;
  }
}
