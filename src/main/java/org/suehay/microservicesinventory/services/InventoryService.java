package org.suehay.microservicesinventory.services;

import org.suehay.microservicesinventory.models.request.OrderItemRequest;
import org.suehay.microservicesinventory.models.response.BaseResponse;

import java.util.List;

public interface InventoryService {

    Boolean isInStock(String sku);
    BaseResponse areInStock(List<OrderItemRequest> items);
}