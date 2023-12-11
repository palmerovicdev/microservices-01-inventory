package org.suehay.microservicesinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suehay.microservicesinventory.models.entities.InventoryEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryEntityRepository extends JpaRepository<InventoryEntity, Long> {
    Optional<InventoryEntity> findBySku(String sku);
    Optional<List<InventoryEntity>> findBySkuIn(List<String> skus);
}