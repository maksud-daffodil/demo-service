package com.diu.edu.demoservice.mapper;

import com.diu.edu.demoservice.dto.SaleMainDTO;
import com.diu.edu.demoservice.entity.SaleMain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMainMapper {

    SaleMainDTO convertToDTO(SaleMain saleMain);
    SaleMain convertToEntity(SaleMainDTO saleMainDTO);

}
