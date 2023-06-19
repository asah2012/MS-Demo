package com.torq.inventory.mapper;

import com.torq.inventory.dto.InventoryDto;
import com.torq.inventory.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoInventoryMapper {

    AutoInventoryMapper MAPPER = Mappers.getMapper(AutoInventoryMapper.class);
    Inventory mapToInventory(InventoryDto inventoryDto);
    InventoryDto mapToInventoryDto(Inventory inventory);
}
