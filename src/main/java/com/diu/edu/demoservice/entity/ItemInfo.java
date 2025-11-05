package com.diu.edu.demoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "UM_DM_Item_Info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedStoredProcedureQuery(
        name = "item_info_save", procedureName = "SP_UM_AS_Item_Info_Save",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "code",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "name",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "price",type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "active",type = Boolean.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "user",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "operation",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_message_code",type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_message_description",type = String.class)
        }
)
public class ItemInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;

    @Column(name = "price")
    Double price;

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
