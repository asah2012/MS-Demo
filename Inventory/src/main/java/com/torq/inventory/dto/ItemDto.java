package com.torq.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Item DTO"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    @NotNull(message = "ItemCode shouldn't be null")
    @Schema(
            description = "ItemCode of the Item"
    )
    private String itemCode;

    @Schema(
            description = "ItemName of the Item"
    )
    @NotBlank(message = "ItemName shouldn't be blank")
    private String itemName;

    @Schema(
            description = "ItemType of the Item"
    )
    @NotEmpty(message = "ItemType shouldn't be empty")
    private String itemType;
}
