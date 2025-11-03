package com.diu.edu.demoservice.service;

import com.diu.edu.demoservice.dao.ItemInfoDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.ItemInfoDTO;
import com.diu.edu.demoservice.entity.ItemInfo;
import com.diu.edu.demoservice.exception.ServiceBusinessException;
import com.diu.edu.demoservice.exception.ServiceNotFoundException;
import com.diu.edu.demoservice.mapper.ItemInfoMapper;
import com.diu.edu.demoservice.repository.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemInfoService {

    private final ItemInfoRepository itemInfoRepository;
    private final ItemInfoMapper itemInfoMapper;

    public List<ItemInfoDTO> findAll() {
        List<ItemInfoDTO> itemInfoDTOS;
        List<ItemInfo> itemInfos = itemInfoRepository.findAll();
        if (!itemInfos.isEmpty()) {
            itemInfoDTOS = itemInfos.stream()
                    .map(itemInfoMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return itemInfoDTOS;
    }

    public List<ItemInfoDTO> findAllByActive() {
        List<ItemInfoDTO> itemInfoDTOS;
        List<ItemInfo> itemInfos = itemInfoRepository.findAllByActive(true);
        if (!itemInfos.isEmpty()) {
            itemInfoDTOS = itemInfos.stream()
                    .map(itemInfoMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return itemInfoDTOS;
    }


    public ItemInfoDTO findById(Long id) {
        ItemInfo itemInfo = itemInfoRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return itemInfoMapper.convertToDTO(itemInfo);
    }

    public ItemInfoDTO findByIdAndActive(Long id) {
        ItemInfo itemInfo = itemInfoRepository.findByIdAndActive(id,true)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return itemInfoMapper.convertToDTO(itemInfo);
    }

    public ApiDTO<?> save(Long id, ItemInfoDAO itemInfoDAO, String user_id) {
        log.info("Step 3 Item info Service");
        Map<String, Object> data = itemInfoRepository.spItemInfoSave(
                id, itemInfoDAO.getCode(), itemInfoDAO.getName(), itemInfoDAO.getPrice(), itemInfoDAO.getActive(), user_id, "E"
        );
        if (Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        log.info("Step 4 Item info Repository");
        ItemInfo itemInfo = itemInfoRepository.findById(Long.parseLong(data.get("out_id").toString()))
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));

        ItemInfoDTO itemInfoDTO = itemInfoMapper.convertToDTO(itemInfo);
        return ApiDTO
                .<ItemInfoDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .data(itemInfoDTO)
                .build();
    }


    public ApiDTO<?> delete(Long id,String user_id) {
        ItemInfoDAO itemInfoDAO = new ItemInfoDAO();
        Map<String, Object> data = itemInfoRepository.spItemInfoSave(
                id, itemInfoDAO.getCode(), itemInfoDAO.getName(), itemInfoDAO.getPrice(), itemInfoDAO.getActive(), user_id, "D"
        );
        if (Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        return ApiDTO
                .<ItemInfoDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .build();
    }
}
