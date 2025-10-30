package com.diu.edu.demoservice.repository;



import com.diu.edu.demoservice.entity.SaleMain;
import com.diu.edu.demoservice.entity.SaleSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Map;

public interface SaleSubRepository extends JpaRepository<SaleSub,Long> {


    List<SaleSub> findAllBySaleMain(SaleMain saleMain);
    @Procedure(name = "sale_sub_save")
    Map<String,Object> spSaleSubSave(Long id, Long sale_main_id, Long item_info_id, Double price,Integer quantity, String user, String operation);


}
