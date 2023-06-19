package com.torq.inventory.repository;

import com.torq.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    public Inventory findByItemCode(String itemCode);


}
