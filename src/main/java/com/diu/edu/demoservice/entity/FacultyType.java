package com.diu.edu.demoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "UM_AS_Faculty_Types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedStoredProcedureQuery(
        name = "faculty_type_save", procedureName = "SP_UM_AS_Faculty_Types_Save",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "code",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "name",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "active",type = Boolean.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "user",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "operation",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_message_code",type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_message_description",type = String.class)
        }
)
public class FacultyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;

    @Column(name = "active")
    Boolean active;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "updated_at")
    LocalDate updatedAt;

}
