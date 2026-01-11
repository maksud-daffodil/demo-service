package com.diu.edu.demoservice.mapper;

import com.diu.edu.demoservice.dto.SaleSubDTO;
import com.diu.edu.demoservice.entity.SaleSub;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleSubMapper {

    SaleSubDTO convertToDTO(SaleSub saleSub);
    SaleSub convertToEntity(SaleSubDTO saleSubDTO);

}
