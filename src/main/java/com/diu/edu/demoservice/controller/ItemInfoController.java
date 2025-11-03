package com.diu.edu.demoservice.controller;


import com.diu.edu.demoservice.dao.ItemInfoDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.ItemInfoDTO;
import com.diu.edu.demoservice.service.ItemInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/demo/iteminfo")
@Tag(name="Item Info")
@Slf4j
public class ItemInfoController {

    private final ItemInfoService itemInfoService;

    @Autowired
    public ItemInfoController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @Operation(summary = "This is to fetch All the Data")
    @GetMapping
    public ResponseEntity<ApiDTO<?>> findAll(){
        List<ItemInfoDTO> itemInfoDTOS = itemInfoService.findAll();
        ApiDTO<List<ItemInfoDTO>> responseDTO = ApiDTO
                .<List<ItemInfoDTO>>builder()
                .status(true)
                .message("Data Found")
                .data(itemInfoDTOS)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch All the Data Active")
    @GetMapping("/active")
    public ResponseEntity<ApiDTO<?>> findAllByActive(){
        List<ItemInfoDTO> itemInfoDTOS = itemInfoService.findAllByActive();
        ApiDTO<List<ItemInfoDTO>> responseDTO = ApiDTO
                .<List<ItemInfoDTO>>builder()
                .status(true)
                .message("Data Found")
                .data(itemInfoDTOS)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @Operation(summary = "Get One Data")
    @GetMapping("/find")
    public ResponseEntity<ApiDTO<?>> findOne(@RequestParam Long id){
        ItemInfoDTO itemInfoDTO = itemInfoService.findById(id);
        ApiDTO<ItemInfoDTO> responseDTO = ApiDTO
                .<ItemInfoDTO>builder()
                .status(true)
                .message("Data Found")
                .data(itemInfoDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Save Data")
    @PostMapping
    public ResponseEntity<ApiDTO<?>> save(@AuthenticationPrincipal Jwt principal, @RequestBody @Valid ItemInfoDAO itemInfoDAO){
        log.info("Step 2 Item info Controller");
        log.info(itemInfoDAO.toString());
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = itemInfoService.save(null,itemInfoDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Data")
    @PutMapping
    public ResponseEntity<ApiDTO<?>> update(@AuthenticationPrincipal Jwt principal, @RequestParam Long id, @RequestBody @Valid ItemInfoDAO itemInfoDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = itemInfoService.save(id,itemInfoDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Data")
    @DeleteMapping
    public ResponseEntity<ApiDTO<?>> delete(@AuthenticationPrincipal Jwt principal, @RequestParam Long id){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = itemInfoService.delete(id,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
