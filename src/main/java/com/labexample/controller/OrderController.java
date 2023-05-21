package com.labexample.controller;

import com.labexample.DTO.OrderDTO;
import com.labexample.Data.OrderData;
import com.labexample.Exception.ClientNotFound;
import com.labexample.Exception.OrderNotFound;
import com.labexample.Mapper.OrderMapper;
import com.labexample.entities.Order;
import com.labexample.repository.ClientRepository;
import com.labexample.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;
    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody @Valid OrderData orderData) {
        return ResponseEntity.ok(orderRepository.save(orderMapper.apply(orderData)));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> update(@RequestParam Long id,
                                             @RequestBody @Valid OrderData orderData) {
        final var orderToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("Order member with id: " + id +" not found!"));
        orderToUpdate.setFromAddress(orderData.getFromAddress());
        orderToUpdate.setToAddress(orderData.getToAddress());
        orderToUpdate.setComplete(orderData.getComplete());
        orderRepository.save(orderToUpdate);
        return ResponseEntity.ok(orderToUpdate);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderRepository.fetchById(id)
                .orElseThrow(() -> new OrderNotFound("Crew member with id: " + id +" not found!")));
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrderDTO>> getAll() {
        return ResponseEntity.ok(orderRepository.fetchAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var order = clientRepository.findById(id)
                        .orElseThrow(()-> new OrderNotFound("Order with" + id + "Not Found"));
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
