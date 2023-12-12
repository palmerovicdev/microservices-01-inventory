package org.suehay.microservicesinventory.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suehay.microservicesinventory.models.request.OrderItemRequest;
import org.suehay.microservicesinventory.models.response.BaseResponse;
import org.suehay.microservicesinventory.services.InventoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get inventory by sku", description = "Get inventory by sku", tags = {"inventory"}, operationId = "get", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Inventory found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Inventory not found")}
    )
    public ResponseEntity<Boolean> isInStock(@PathVariable String sku) {
        return ResponseEntity.status(HttpStatus.OK).body(this.inventoryService.isInStock(sku));
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get inventory by sku", description = "Get inventory by sku", tags = {"inventory"}, operationId = "get", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Inventory found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Inventory not found")}
    )
    public ResponseEntity<BaseResponse> areInStock(@RequestBody List<OrderItemRequest> items) {
        return ResponseEntity.status(HttpStatus.OK).body(this.inventoryService.areInStock(items));
    }
}