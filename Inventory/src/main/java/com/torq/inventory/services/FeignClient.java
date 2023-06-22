package com.torq.inventory.services;

import com.torq.inventory.dto.ItemDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "ORDER-SERVICE")
public interface FeignClient {
    @GetMapping("item/item-code/{itemCodeValue}")
    ItemDto getItemByItemCode(@PathVariable("itemCodeValue") String itemCode);
}
