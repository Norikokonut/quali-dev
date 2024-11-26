package org.ormi.priv.tfa.orderflow.product.registry.aggregate.repository.model;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * Product registry event entity.
 */
@MongoEntity(collection = "product_registry_events")
public abstract class ProductRegistryEventEntity {
  /** id of the event */
	public ObjectId id;
  /** id of the event as a String */
  public String eventId;
  /** type of the event */
  public String eventType = getEventType();
  /** id of the aggregate root */
  public String aggregateRootId;
  /** version of the event */
  public long version;
  /** timestamp of the event */
  public long timestamp;

  abstract String getEventType();
}
