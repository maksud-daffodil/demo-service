package com.diu.edu.demoservice.dao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SaleSubDAO {

    @NotNull(message = "Sale Main shouldn't be NULL OR EMPTY")
    Long saleMainId;

    @NotNull(message = "Item Info shouldn't be NULL OR EMPTY")
    Long itemInfoId;

    @NotNull(message = "Unit price shouldn't be NULL OR EMPTY")
    Double price;

    @NotNull(message = "Quantity shouldn't be NULL OR EMPTY")
    Integer quantity;
}
