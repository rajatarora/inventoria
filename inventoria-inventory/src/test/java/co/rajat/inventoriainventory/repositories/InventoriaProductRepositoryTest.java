package co.rajat.inventoriainventory.repositories;

import co.rajat.inventoriainventory.models.InventoriaProduct;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class InventoriaProductRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:8"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getConnectionString);
        registry.add("spring.data.mongodb.database", () -> "inventoria-inventory");
    }

    @Autowired
    private InventoriaProductRepository inventoriaProductRepository;

    @Test
    public void testOne() {
        InventoriaProduct product = Instancio.create(InventoriaProduct.class);
        InventoriaProduct saved = inventoriaProductRepository.save(product);

        Optional<InventoriaProduct> retrieved = inventoriaProductRepository.findById(product.getId());

        assertThat(retrieved)
                .isPresent()
                .get()
                .extracting(InventoriaProduct::getName)
                .isEqualTo(product.getName());
    }

}