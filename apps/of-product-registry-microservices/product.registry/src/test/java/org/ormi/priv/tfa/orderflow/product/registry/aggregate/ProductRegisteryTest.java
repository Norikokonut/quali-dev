package org.ormi.priv.tfa.orderflow.product.registry.aggregate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.ormi.priv.tfa.orderflow.lib.publishedlanguage.command.ProductRegistryCommand;
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
        public void it_should_handleValidProductRegistryCommand() {
            // Given
            ProductRegistry productRegistry = new ProductRegistry(new ProductRegistryService());
            ProductId productId = new ProductId();
            Product product = new Product(productId, "Test 1", "A test product");
            ProductRegistryCommand command = new ProductRegistryCommand(productId, product);
            
            // When
            productRegistry.handle(command);
            
            // Then
            assertTrue(productRegistry.hasProductWithId(productId));
            assertEquals(product, productRegistry.getProduct(productId));
            
            // When
            productRegistry.handle(command);
            
            // Then
            assertEquals(product, productRegistry.getProduct(productId));
            
            // When
            productRegistry.handle(new ProductRegistryCommand(productId, null));
            
            // Then
            assertFalse(productRegistry.hasProduct(productId));
        }
        
    }
}
