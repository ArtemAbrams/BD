package com.labexample.repository;

import com.labexample.DTO.ClientDto;
import com.labexample.DTO.OrderDTO;
import com.labexample.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select new com.labexample.DTO.OrderDTO(c.fromAddress, c.toAddress, c.complete)" +
            " from Order c where c.Id = :id")
    Optional<OrderDTO> fetchById(Long id);
    @Query("select new com.labexample.DTO.OrderDTO(c.fromAddress, c.toAddress, c.complete)" +
            " from Order c")
    List<OrderDTO> fetchAll();

    @Query(value = "select distinct concat('order_', op.order_id) from order_product op" +
                   " where (op.product_id in (select op_1.product_id from order_product op_1 where op_1.order_id = :orderId)" +
                   "  and op.order_id <> :orderId)" , nativeQuery = true)
    List<String> specialQuery6(Long orderId);

    @Query(value = "SELECT concat('order_', o.id)" +
            " FROM orders o" +
            " WHERE o.id <> :orderId AND NOT EXISTS (" +
            "        SELECT op2.product_id" +
            "        FROM order_product op2" +
            "        WHERE op2.order_id = :orderId" +
            "        EXCEPT" +
            "        SELECT op.product_id" +
            "        FROM order_product op" +
            "        WHERE op.order_id = o.id" +
            "    )" +
            " order by o.id", nativeQuery = true)
    List<String> specialQuery7(Long orderId);
    @Query(value = "select distinct concat('order_', order_id) from order_product pr" +
            " where pr.order_id <> :orderId and not exists(" +
            "        (" +
            "            select p.id from products p where p.id IN (select op.product_id from order_product op where op.order_id=pr.order_id)" +
            "        )" +
            "        except" +
            "        (" +
            "            (select p.id from products p where p.id in (select o.product_id from order_product o where o.order_id=:orderId))" +
            "        )" +
            "    )" +
            "    and not exists(" +
            "        (" +
            "            (select p.id from products p where p.id in (select o.product_id from order_product o where o.order_id=:orderId))" +
            "        )" +
            "        except" +
            "        (" +
            "            select p.id from products p where p.id IN (select op.product_id from order_product op where op.order_id=pr.order_id)" +
            "        )" +
            "    );",nativeQuery = true)
    List<String> specialQuery8(Long orderId);
    @Query(value = "select distinct concat('order_', order_id) from order_product pr" +
            " where pr.order_id <> :orderId and not exists(" +
            "        (" +
            "            (select p.id from products p where p.id in (select o.product_id from order_product o where o.order_id=:orderId))" +
            "        )" +
            "        except" +
            "        (" +
            "            select p.id from products p where p.id IN (select op.product_id from order_product op where op.order_id=pr.order_id)" +
            "        )" +
            "    )" +
            "  and EXISTS(" +
            "        (" +
            "            (select p.id from products p where p.id IN (select op.product_id from order_product op where op.order_id=pr.order_id))" +
            "        )" +
            "        except" +
            "        (" +
            "                (select p.id from products p where p.id in (select o.product_id from order_product o where o.order_id=:orderId))" +
            "        )" +
            "    );",nativeQuery = true)
    List<String> specialQuery9(Long orderId);
}
