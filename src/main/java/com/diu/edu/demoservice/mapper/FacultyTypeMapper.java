package com.diu.edu.demoservice.mapper;





import com.diu.edu.demoservice.dto.FacultyTypeDTO;
import com.diu.edu.demoservice.entity.FacultyType;
import org.mapstruct.Mapper;

@Mapper
public interface FacultyTypeMapper {

    FacultyTypeDTO convertToDTO(FacultyType facultyType);
    FacultyType convertToEntity(FacultyTypeDTO facultyTypeDTO);

}
