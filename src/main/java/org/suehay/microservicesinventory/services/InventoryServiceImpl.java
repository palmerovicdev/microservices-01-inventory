package org.suehay.microservicesinventory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suehay.microservicesinventory.models.request.OrderItemRequest;
import org.suehay.microservicesinventory.models.response.BaseResponse;
import org.suehay.microservicesinventory.repositories.InventoryEntityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final InventoryEntityRepository inventoryEntityRepository;

    @Override
    public Boolean isInStock(String sku) {
        return inventoryEntityRepository.findBySku(sku).filter(inventoryEntity -> inventoryEntity.getQuantity() > 0).isPresent();
    }

    @Override
    public BaseResponse areInStock(List<OrderItemRequest> items) {
        var errors = new ArrayList<String>();
        var inventories = inventoryEntityRepository.findBySkuIn(items.stream().map(OrderItemRequest::getSku).toList());
        if (inventories.isPresent()) {
            if (inventories.get().isEmpty()) {
                errors.add("There is no enough stock for the order");
                return new BaseResponse(errors.toArray(String[]::new));
            }
            inventories.get().forEach(inventoryEntity -> {
                var item = items.stream().filter(orderItemRequest -> orderItemRequest.getSku().equals(inventoryEntity.getSku())).findFirst();
                if (item.isPresent()) {
                    if (inventoryEntity.getQuantity() < item.get().getQuantity()) {
                        errors.add(String.format("There is no enough stock for the item with sku %s", item.get().getSku()));
                    }
                }
            });
        } else {
            items.forEach(orderItemRequest -> errors.add(String.format("There is no enough stock for the item with sku %s", orderItemRequest.getSku())));
        }
        return new BaseResponse(errors.toArray(String[]::new));
    }

}