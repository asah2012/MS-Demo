package com.torq.inventory.services;

import com.torq.inventory.dto.APIClient;
import com.torq.inventory.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    public InventoryDto createInventory(InventoryDto inventory);
    public InventoryDto getInventoryById(Long id);
    public APIClient getInventoryWithItemDetailsById(Long id);

    public InventoryDto getInventoryByItemCode(String itemCode);

    public List<InventoryDto> getAllInventory();
    public InventoryDto updateInventory(InventoryDto inventory);

    public String deleteInventory(Long id);
    public String deleteInventoryByItemCode(String itemCode);

}
