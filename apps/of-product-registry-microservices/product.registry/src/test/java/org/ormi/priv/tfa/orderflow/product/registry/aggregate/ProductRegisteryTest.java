package org.ormi.priv.tfa.orderflow.product.registry.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.command.RegisterProduct;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.command.RemoveProduct;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.command.UpdateProduct;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductRegistered;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductRemoved;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.event.ProductUpdated;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.valueobject.ProductId;
import org.ormi.priv.tfa.orderflow.product.registry.aggregate.service.ProductRegistryService;

public class ProductRegisteryTest {

    @Nested
    public class Handle {
        
        /**
         * Tester la gestion d'une commande de registre de produits valide
         *  Le produit doit être enregistré
         *  Le produit doit être mis à jour
         *  Le produit doit être supprimé
         */
        @Test
        public void it_should_handleValidAndInvalidProductRegistryCommands() {
            // Given
            ProductRegistry productRegistry = new ProductRegistry(new ProductRegistryService());
            ProductId productId = new ProductId();
            Product product = new Product(productId, "Test 1", "A test product");
            Product updatedProduct = new Product(productId, "Updated Test", "Updated product description");

            // 1. Tester l'enregistrement
            RegisterProduct registerCommand = new RegisterProduct(product.getName(), product.getProductDescription());
            productRegistry.handle(registerCommand).await().indefinitely();

            // Then
            assertTrue(productRegistry.hasProductWithId(productId));
            assertTrue(productRegistry.hasProduct(new Product(productId, "Test 1", "A test product")));

            // 2. Tester la mise à jour
            UpdateProduct updateCommand = new UpdateProduct(productId, updatedProduct.getName(), updatedProduct.getProductDescription());
            productRegistry.handle(updateCommand).await().indefinitely();

            // Then
            assertTrue(productRegistry.hasProductWithId(productId));
            assertTrue(productRegistry.hasProduct(new Product(productId, "Updated Test", "Updated product description")));

            // 3. Tester la suppression
            RemoveProduct removeCommand = new RemoveProduct(productId);
            productRegistry.handle(removeCommand).await().indefinitely();

            // Then
            assertFalse(productRegistry.hasProductWithId(productId));

            // 4. Tester une commande nulle
            try {
                productRegistry.handle(null).await().indefinitely();
                fail("Expected an IllegalArgumentException to be thrown for a null command");
            } catch (IllegalArgumentException e) {
                assertEquals("Unhandled command type", e.getMessage());
            }
        }
    }

        @Nested
    public class Apply {
        
        /**
         * Tester l'application d'un événement de registre de produits valide.
         *  Le produit doit être enregistré.
         *  Le produit doit être mis à jour.
         *  Le produit doit être supprimé.
         */
        @Test
        public void it_should_applyValidProductRegistryEvents() {
            // Given
            ProductRegistry productRegistry = new ProductRegistry(new ProductRegistryService());
            ProductId productId = new ProductId();

            // 1. Tester l'application d'un événement d'enregistrement
            ProductRegistered registeredEvent = new ProductRegistered(productId, "Test 1", "A test product");
            productRegistry.apply(registeredEvent);

            // Then
            assertTrue(productRegistry.hasProductWithId(productId));
            assertTrue(productRegistry.hasProduct(new Product(productId, "Test 1", "A test product")));

            // 2. Tester l'application d'un événement de mise à jour
            ProductUpdated updatedEvent = new ProductUpdated(productId, "Updated Test", "Updated product description");
            productRegistry.apply(updatedEvent);

            // Then
            assertTrue(productRegistry.hasProductWithId(productId));
            assertTrue(productRegistry.hasProduct(new Product(productId, "Updated Test", "Updated product description")));

            // 3. Tester l'application d'un événement de suppression
            ProductRemoved removedEvent = new ProductRemoved(productId);
            productRegistry.apply(removedEvent);

            // Then
            assertFalse(productRegistry.hasProductWithId(productId));
        }

        /**
         * Tester l'application d'un événement de registre de produits nul.
         *  L'événement doit échouer.
         */
        @Test
        public void it_should_failToApplyNullEvent() {
            // Given
            ProductRegistry productRegistry = new ProductRegistry(new ProductRegistryService());

            // When / Then
            try {
                productRegistry.apply(null);
                fail("Expected an IllegalArgumentException to be thrown for a null event");
            } catch (IllegalArgumentException e) {
                assertEquals("Unhandled event type", e.getMessage());
            }
        }
    }

}
