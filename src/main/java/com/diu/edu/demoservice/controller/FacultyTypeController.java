package com.diu.edu.demoservice.controller;


import com.diu.edu.demoservice.dao.FacultyTypeDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.FacultyTypeDTO;
import com.diu.edu.demoservice.service.FacultyTypeService;
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
@RequestMapping("/api/faculty/type")
@Tag(name="Faculty Type")
public class FacultyTypeController {

    private final FacultyTypeService facultyTypeService;

    @Autowired
    public FacultyTypeController(FacultyTypeService facultyTypeService) {
        this.facultyTypeService = facultyTypeService;
    }

    @Operation(summary = "This is to fetch All the Data")
    @GetMapping
    public ResponseEntity<ApiDTO<?>> findAll(){
        List<FacultyTypeDTO> facultyTypeDTOS = facultyTypeService.findAll();
        ApiDTO<List<FacultyTypeDTO>> responseDTO = ApiDTO
                .<List<FacultyTypeDTO>>builder()
                .status(true)
                .message("Data Found")
                .data(facultyTypeDTOS)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch All the Data Active")
    @GetMapping("/active")
    public ResponseEntity<ApiDTO<?>> findAllByActive(){
        List<FacultyTypeDTO> facultyTypeDTOS = facultyTypeService.findAllByActive();
        ApiDTO<List<FacultyTypeDTO>> responseDTO = ApiDTO
                .<List<FacultyTypeDTO>>builder()
                .status(true)
                .message("Data Found")
                .data(facultyTypeDTOS)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @Operation(summary = "Get One Data")
    @GetMapping("/find")
    public ResponseEntity<ApiDTO<?>> findOne(@RequestParam Long id){
        FacultyTypeDTO facultyTypeDTO = facultyTypeService.findById(id);
        ApiDTO<FacultyTypeDTO> responseDTO = ApiDTO
                .<FacultyTypeDTO>builder()
                .status(true)
                .message("Data Found")
                .data(facultyTypeDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Save Data")
    @PostMapping
    public ResponseEntity<ApiDTO<?>> save(@AuthenticationPrincipal Jwt principal, @RequestBody @Valid FacultyTypeDAO facultyTypeDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = facultyTypeService.save(null, facultyTypeDAO, user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Data")
    @PutMapping
    public ResponseEntity<ApiDTO<?>> update(@AuthenticationPrincipal Jwt principal, @RequestParam Long id, @RequestBody @Valid FacultyTypeDAO facultyTypeDAO){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = facultyTypeService.save(id,facultyTypeDAO,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Data")
    @DeleteMapping
    public ResponseEntity<ApiDTO<?>> delete(@AuthenticationPrincipal Jwt principal, @RequestParam Long id){
        String user_id = principal.getClaimAsString("preferred_username");
        ApiDTO<?> responseDTO = facultyTypeService.delete(id,user_id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
