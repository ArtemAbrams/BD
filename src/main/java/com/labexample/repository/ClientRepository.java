package com.labexample.repository;

import com.labexample.DTO.ClientAvgProducts;
import com.labexample.DTO.ClientDto;
import com.labexample.entities.Client;
import com.labexample.enums.Transport;
import com.labexample.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select new com.labexample.DTO.ClientDto(c.name, c.surname,c.birthday,c.mobilePhone,c.gender)" +
            " from Client c where c.Id = :id")
    Optional<ClientDto> fetchById(Long id);
    @Query("select new com.labexample.DTO.ClientDto(c.name, c.surname,c.birthday,c.mobilePhone,c.gender)" +
             " from Client c")
    List<ClientDto> fetchAll();

    @Query(value = "select new com.labexample.DTO.ClientDto(cm.name, cm.surname,cm.birthday,cm.mobilePhone,cm.gender)" +
                    "  from Client cm " +
                    "  inner join cm.orders c " +
                    "  inner join c.delivery t " +
                    "  where t.transport = :transport and c.created>:time")
    List<ClientDto> specialQuery1(LocalDateTime time, Transport transport);

    @Query(value = " select new com.labexample.DTO.ClientAvgProducts(c.name, c.surname, avg(p.price))" +
                    " from  Client c" +
                    " inner join  c.orders o" +
                    " inner join  o.products p" +
                    " where p.type=:type and c.name=:name"+
                    " group by c.Id")
    List<ClientAvgProducts> specialQuery3(Type type, String name);
    @Query(value = "select new com.labexample.DTO.ClientDto(c.name, c.surname,c.birthday,c.mobilePhone,c.gender)" +
            "from Client c " +
            " inner join c.orders o" +
            " inner join o.products p" +
            " group by c.Id" +
            " having count(p) > :count")
    List<ClientDto> specialQuery5(Integer count);
}
