package com.torq.inventory.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;
    //@NotNull(message = "ItemCode can't be null")
    private String itemCode;

    //@NotEmpty(message = "ATP can't be empty")
    private Double atp = 0.0;
    private Double inHand = 0.0;

    private LocalDateTime updated_at;
}
