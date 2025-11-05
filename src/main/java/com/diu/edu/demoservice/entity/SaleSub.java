package com.diu.edu.demoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "UM_DM_Sale_Sub")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedStoredProcedureQuery(
        name = "sale_sub_save", procedureName = "SP_UM_DM_Sale_Sub_Save",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "sale_main_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "item_info_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "price",type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "quantity",type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "user",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "operation",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_message_code",type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_message_description",type = String.class)
        }
)
public class SaleSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "sale_main_id")
    SaleMain saleMain;

    @ManyToOne
    @JoinColumn(name = "item_info_id")
    ItemInfo itemInfo;

    @Column(name = "price")
    Double price;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "updated_at")
    LocalDate updatedAt;

}
