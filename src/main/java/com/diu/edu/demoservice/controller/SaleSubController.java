package com.diu.edu.demoservice.controller;


import com.diu.edu.demoservice.dao.SaleSubDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.SaleSubDTO;
import com.diu.edu.demoservice.service.SaleSubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/demo/salesub")
@Tag(name="Sale Sub")
public class SaleSubController {

    SaleSubService saleSubService;

    @Autowired
    public SaleSubController(SaleSubService saleSubService) {
        this.saleSubService = saleSubService;
    }

    @Operation(summary = "This is to fetch All the Data By Sale Main")
    @GetMapping
    public ResponseEntity<ApiDTO<?>> findAll(@RequestParam Long saleMainId){
        List<SaleSubDTO> saleSubDTOS = saleSubService.findAll(saleMainId);
        ApiDTO<List<SaleSubDTO>> responseDTO = ApiDTO
                .<List<SaleSubDTO>>builder()
                .status(true)
                .message("Data Found")
                .data(saleSubDTOS)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



    @Operation(summary = "Get One Data")
    @GetMapping("/find")
    public ResponseEntity<ApiDTO<?>> findOne(@RequestParam Long id){
        SaleSubDTO saleSubDTO = saleSubService.findById(id);
        ApiDTO<SaleSubDTO> responseDTO = ApiDTO
                .<SaleSubDTO>builder()
                .status(true)
                .message("Data Found")
                .data(saleSubDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Save Data")
    @PostMapping
    public ResponseEntity<ApiDTO<?>> save(@AuthenticationPrincipal Jwt principal, @RequestBody @Valid SaleSubDAO saleSubDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = saleSubService.save(null,saleSubDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Data")
    @PutMapping
    public ResponseEntity<ApiDTO<?>> update(@AuthenticationPrincipal Jwt principal, @RequestParam Long id, @RequestBody @Valid SaleSubDAO saleSubDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = saleSubService.save(id,saleSubDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Data")
    @DeleteMapping
    public ResponseEntity<ApiDTO<?>> delete(@AuthenticationPrincipal Jwt principal, @RequestParam Long id){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = saleSubService.delete(id,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
