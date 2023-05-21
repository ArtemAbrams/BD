package com.labexample.repository;

import com.labexample.DTO.ClientDto;
import com.labexample.DTO.DeliveryDTO;
import com.labexample.entities.Delivery;
import com.labexample.enums.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query(value = "select distinct new com.labexample.DTO.DeliveryDTO(d.name, p.name, d.mobilePhone, d.gender, d.transport)" +
                   " from Delivery d" +
                   " inner join d.orders o " +
                   " inner join o.products p" +
                   " where p.color in (:color) and p.size<:size")
    List<DeliveryDTO> specialQuery2(List<Color> color, Double size);
    @Query(value = "SELECT c.mobile_phone FROM clients c WHERE c.id IN" +
            "     (SELECT o.client_id FROM orders o WHERE o.delivery_id IN" +
            "        ((SELECT d.id FROM deliveries d" +
            "            EXCEPT" +
            "        (SELECT d.id FROM deliveries d WHERE d.name = :name ))))", nativeQuery = true)
    List<String> specialQuery4(String name);


}
