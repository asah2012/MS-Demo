package com.torq.inventory.services.impl;

import com.torq.inventory.dto.APIClient;
import com.torq.inventory.dto.InventoryDto;
import com.torq.inventory.dto.ItemDto;
import com.torq.inventory.entity.Inventory;
import com.torq.inventory.exception.ResourceNotFoundException;
import com.torq.inventory.mapper.AutoInventoryMapper;
import com.torq.inventory.repository.InventoryRepository;
import com.torq.inventory.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    //private RestTemplate restTemplate;
    private WebClient webClient;
    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        Inventory currentInventory = inventoryRepository.findByItemCode(inventoryDto.getItemCode());
        Inventory inventory;

        if(currentInventory != null){
            InventoryDto currentInventoryDto = AutoInventoryMapper.MAPPER.mapToInventoryDto(currentInventory);
            currentInventoryDto.setAtp(currentInventoryDto.getAtp() + inventoryDto.getAtp());
            currentInventoryDto.setInHand(currentInventoryDto.getInHand() + inventoryDto.getInHand());
            inventory = AutoInventoryMapper.MAPPER.mapToInventory(currentInventoryDto);
        }
        else{
            inventory = AutoInventoryMapper.MAPPER.mapToInventory(inventoryDto);
        }
        Inventory savedInventory = inventoryRepository.save(inventory);
        InventoryDto savedInventoryDto = AutoInventoryMapper.MAPPER.mapToInventoryDto(savedInventory);
        return savedInventoryDto;
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if(inventoryOptional == null)
            throw new ResourceNotFoundException("Inventory","InventoryId" , id.toString());
        return AutoInventoryMapper.MAPPER.mapToInventoryDto(inventoryOptional.get());

    }

    @Override
    public InventoryDto getInventoryByItemCode(String itemCode) {
        Inventory inventory =  inventoryRepository.findByItemCode(itemCode);
        if(inventory == null)
            throw new ResourceNotFoundException("Inventory","ItemCode ", itemCode);

        return AutoInventoryMapper.MAPPER.mapToInventoryDto(inventory);
    }


    @Override
    public List<InventoryDto> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<InventoryDto> inventoryDtoList = new ArrayList<>();

        inventoryList.forEach((inventory)->{
            inventoryDtoList.add(AutoInventoryMapper.MAPPER.mapToInventoryDto(inventory));

        });
        return inventoryDtoList;
    }

    @Override
    public APIClient getInventoryWithItemDetailsById(Long id){
        InventoryDto inventoryDto = getInventoryById(id);
        //ResponseEntity<ItemDto> response = restTemplate.getForEntity("http://localhost:8080/item/item-code/"+inventoryDto.getItemCode(), ItemDto.class);
        //ItemDto itemDto = response.getBody();

        ItemDto itemDto = webClient.get().uri("http://localhost:8080/item/item-code/"+inventoryDto.getItemCode()).retrieve().bodyToMono(ItemDto.class).block();
        APIClient apiClientResponse = new APIClient();
        apiClientResponse.setInventory(inventoryDto);
        apiClientResponse.setItem(itemDto);
        return apiClientResponse;
    }
    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto) {
        //Updating the inventory instead of adding it
        Inventory currentInventory = AutoInventoryMapper.MAPPER.mapToInventory(getInventoryById(inventoryDto.getId()));
        if(inventoryDto.getAtp() != null)
            currentInventory.setAtp(inventoryDto.getAtp());
        if(inventoryDto.getInHand() != null)
            currentInventory.setInHand(inventoryDto.getInHand());
        return AutoInventoryMapper.MAPPER.mapToInventoryDto(inventoryRepository.save(currentInventory));

    }

    @Override
    public String deleteInventory(Long id) {
        Inventory origInventory = AutoInventoryMapper.MAPPER.mapToInventory(getInventoryById(id));
        inventoryRepository.delete(origInventory);
        return "Inventory deleted successfully";
    }

    @Override
    public String deleteInventoryByItemCode(String itemCode) {
        Inventory inventory = AutoInventoryMapper.MAPPER.mapToInventory(getInventoryByItemCode(itemCode));
        inventoryRepository.delete(inventory);
        return "Inventory deleted Successfully";
    }

}
