package com.diu.edu.demoservice.repository;

import com.diu.edu.demoservice.entity.FacultyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FacultyTypeRepository extends JpaRepository<FacultyType, Long> {

    Optional<FacultyType> findByIdAndActive(Long id, boolean b);

    List<FacultyType> findAllByActive(boolean b);

    @Procedure(name = "faculty_type_save")
    Map<String, Object> spFacultyTypeSave(Long id, String code, String name, Boolean active, String user, String operation);

}
