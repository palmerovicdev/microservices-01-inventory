package org.suehay.microservicesinventory.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.suehay.microservicesinventory.models.entities.InventoryEntity;
import org.suehay.microservicesinventory.repositories.InventoryEntityRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final InventoryEntityRepository inventoryEntityRepository;

    @Override
    public void run(String... args){
        log.info("Loading data...");
        inventoryEntityRepository.saveAll(
                List.of(
                        InventoryEntity.builder().sku("00001").quantity(10).build(),
                        InventoryEntity.builder().sku("00002").quantity(20).build(),
                        InventoryEntity.builder().sku("00003").quantity(30).build(),
                        InventoryEntity.builder().sku("00004").quantity(0).build()
                       ));
        log.info("...finished loading data.");
    }
}