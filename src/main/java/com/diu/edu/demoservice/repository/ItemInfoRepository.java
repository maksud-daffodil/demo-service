package com.diu.edu.demoservice.repository;

import com.diu.edu.demoservice.entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemInfoRepository extends JpaRepository<ItemInfo,Long> {

    Optional<ItemInfo> findByIdAndActive(Long id, boolean b);

    List<ItemInfo> findAllByActive(boolean b);

    @Procedure(name = "item_info_save")
    Map<String,Object> spItemInfoSave(Long id, String code, String name, Double price, Boolean active, String user, String operation);


}
