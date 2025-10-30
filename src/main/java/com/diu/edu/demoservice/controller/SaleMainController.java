package com.diu.edu.demoservice.controller;


import com.diu.edu.demoservice.dao.SaleMainDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.SaleMainDTO;
import com.diu.edu.demoservice.service.SaleMainService;
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
@RequestMapping("/api/demo/salemain")
@Tag(name="Sale Main")
public class SaleMainController {

    SaleMainService saleMainService;

    @Autowired
    public SaleMainController(SaleMainService saleMainService) {
        this.saleMainService = saleMainService;
    }

    @Operation(summary = "This is to fetch All the Data")
    @GetMapping
    public ResponseEntity<ApiDTO<?>> findAll(){
        List<SaleMainDTO> saleMainDTOS = saleMainService.findAll();
        ApiDTO<List<SaleMainDTO>> responseDTO = ApiDTO
                .<List<SaleMainDTO>>builder()
                .status(true)
                .message("Data Found")
                .data(saleMainDTOS)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



    @Operation(summary = "Get One Data")
    @GetMapping("/find")
    public ResponseEntity<ApiDTO<?>> findOne(@RequestParam Long id){
        SaleMainDTO saleMainDTO = saleMainService.findById(id);
        ApiDTO<SaleMainDTO> responseDTO = ApiDTO
                .<SaleMainDTO>builder()
                .status(true)
                .message("Data Found")
                .data(saleMainDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Save Data")
    @PostMapping
    public ResponseEntity<ApiDTO<?>> save(@AuthenticationPrincipal Jwt principal, @RequestBody @Valid SaleMainDAO saleMainDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = saleMainService.save(null,saleMainDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Data")
    @PutMapping
    public ResponseEntity<ApiDTO<?>> update(@AuthenticationPrincipal Jwt principal, @RequestParam Long id, @RequestBody @Valid SaleMainDAO saleMainDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = saleMainService.save(id,saleMainDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Data")
    @DeleteMapping
    public ResponseEntity<ApiDTO<?>> delete(@AuthenticationPrincipal Jwt principal, @RequestParam Long id){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = saleMainService.delete(id,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
