package com.diu.edu.demoservice.mapper;

import com.diu.edu.demoservice.dto.ItemInfoDTO;
import com.diu.edu.demoservice.entity.ItemInfo;
import org.mapstruct.Mapper;

@Mapper
public interface ItemInfoMapper {

    ItemInfoDTO convertToDTO(ItemInfo itemInfo);
    ItemInfo convertToEntity(ItemInfoDTO itemInfoDTO);

}
