package com.diu.edu.demoservice.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SaleMainDAO {

    @NotNull(message = "Sale Date shouldn't be NULL OR EMPTY")
    LocalDate saleDate;

    @NotBlank(message = "Person shouldn't be NULL OR EMPTY")
    String person;

}
