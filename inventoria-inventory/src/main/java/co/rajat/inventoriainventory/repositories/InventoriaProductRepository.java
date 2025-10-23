package co.rajat.inventoriainventory.repositories;

import co.rajat.inventoriainventory.models.InventoriaProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface InventoriaProductRepository extends MongoRepository<InventoriaProduct, UUID> {

}
