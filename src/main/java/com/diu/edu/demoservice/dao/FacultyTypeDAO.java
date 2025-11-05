package com.diu.edu.demoservice.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FacultyTypeDAO {

    @NotBlank(message = "Code shouldn't be NULL OR EMPTY")
    String code;

    @NotBlank(message = "Name shouldn't be NULL OR EMPTY")
    String name;

    @NotNull(message = "Active shouldn't be NULL OR EMPTY")
    Boolean active;
}
