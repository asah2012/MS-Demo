package com.torq.inventory.controller;

import com.torq.inventory.dto.APIClient;
import com.torq.inventory.dto.InventoryDto;
import com.torq.inventory.services.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inventory related APIs",
        description = "CRUD operations on Inventory entity")
@RestController
@RequestMapping("inventory")
public class InventoryController {


    @Autowired
    private InventoryService inventoryService;

    @Operation(
            summary = "Create an inventory",
            description = "Create an inventory"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){
        InventoryDto savedInventoryDto = inventoryService.createInventory(inventoryDto);
        return new ResponseEntity<>(savedInventoryDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get inventory by inventory Id" ,
            description = "Get inventory by inventory Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )

    @GetMapping("{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable("id") Long inventoryId){
        InventoryDto inventoryDto = inventoryService.getInventoryById(inventoryId);
        return ResponseEntity.ok(inventoryDto);
    }


    @Operation(
            summary = "Get All inventory" ,
            description = "Get All inventory"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventory(){
        List<InventoryDto> inventoryDtoList = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryDtoList);
    }


    @Operation(
            summary = "Get inventory by item code" ,
            description = "Get inventory by item code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("itemCode/{itemCodeValue}")
    public ResponseEntity<InventoryDto> getInventoryByItemCode(@PathVariable("itemCodeValue") String itemCode){
        InventoryDto inventoryDto = inventoryService.getInventoryByItemCode(itemCode);
        return ResponseEntity.ok(inventoryDto);
    }


    @Operation(
            summary = "Get inventory by inventory Id with item details" ,
            description = "Get inventory by inventory Id with item details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("inventory-with-item-details/{inventoryId}")
    public ResponseEntity<APIClient> getInventoryWithItemDetails(@PathVariable("inventoryId") Long inventoryId){
        APIClient response = inventoryService.getInventoryWithItemDetailsById(inventoryId);
        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "update inventory by inventory Id" ,
            description = "update inventory by inventory Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id , @RequestBody InventoryDto inventoryDto){
        inventoryDto.setId(id);
        InventoryDto updatedInventoryDto = inventoryService.updateInventory(inventoryDto);
        return ResponseEntity.ok(updatedInventoryDto);
    }

    /*
    @PutMapping("itemCode/{itemCodeValue}")
    public ResponseEntity<InventoryDto> updateInventoryByItemCode(@PathVariable("itemCodeValue") String itemCodeValue, @RequestBody InventoryDto inventoryDto){
        inventoryDto.setItemCode(itemCodeValue);
        InventoryDto updatedInventoryDto = inventoryService.updateInventory(inventoryDto);
        return ResponseEntity.ok(updatedInventoryDto);
    }
     */


    @Operation(
            summary = "Delete inventory by inventory Id" ,
            description = "Delete inventory by inventory Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        String response = inventoryService.deleteInventory(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @Operation(
            summary = "Get inventory by item code" ,
            description = "Get inventory by item code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("itemCode/{itemCodeValue}")
    public ResponseEntity<String> deleteInventoryByItemCode(@PathVariable("itemCodeValue") String itemCode){
        String response = inventoryService.deleteInventoryByItemCode(itemCode);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
