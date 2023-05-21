package com.labexample.controller;

import com.labexample.DTO.ClientDto;
import com.labexample.Data.ClientData;
import com.labexample.Exception.ClientNotFound;
import com.labexample.Exception.OrderNotFound;
import com.labexample.Mapper.ClientMapper;
import com.labexample.entities.Client;
import com.labexample.repository.ClientRepository;
import com.labexample.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
   private final ClientRepository clientRepository;
   private final OrderRepository orderRepository;
   private final ClientMapper clientMapper;
   @PostMapping("/create")
   public ResponseEntity<Client> create(@RequestBody @Valid ClientData clientData) {
      return ResponseEntity.ok(clientRepository.save(clientMapper.apply(clientData)));
   }

   @PutMapping("/update")
   public ResponseEntity<Client> update(@RequestParam Long id,
                                      @RequestBody @Valid ClientData crewData) {
      final var clientToUpdate = clientRepository.findById(id)
              .orElseThrow(() -> new ClientNotFound("Client with id: " + id +" not found!"));
      clientToUpdate.setName(crewData.getName());
      clientToUpdate.setSurname(crewData.getSurname());
      clientToUpdate.setBirthday(crewData.getBirthday());
      clientToUpdate.setGender(crewData.getGender());
      clientToUpdate.setPassword(crewData.getPassword());
      if (isNull(crewData.getOrders())) {
         clientToUpdate.setOrders(clientToUpdate.getOrders());
      } else {
         crewData.getOrders().forEach(clientToUpdate::addOrder);
      }
      clientRepository.save(clientToUpdate);
      return ResponseEntity.ok(clientToUpdate);
   }

   @GetMapping("/get/{id}")
   public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
      return ResponseEntity.ok(clientRepository.fetchById(id)
              .orElseThrow(() -> new ClientNotFound("Client with id: " + id +" not found!")));
   }
   @PutMapping("/joinOrder")
   public ResponseEntity<String> joinOrder(@RequestParam Long orderId,
                                           @RequestParam Long clientId)
   {
      var order = orderRepository.findById(orderId)
              .orElseThrow(() -> new OrderNotFound("Order with" + orderId + "Not Found"));
      var client = clientRepository.findById(clientId)
              .orElseThrow(() -> new ClientNotFound("Client with id" + clientId + "Not Found"));
      var orderList = client.getOrders();
      orderList.add(order);
      client.setOrders(orderList);
      clientRepository.save(client);
      return ResponseEntity.ok()
              .body("You add order");
   }
   @GetMapping("/get")
   public ResponseEntity<List<ClientDto>> getAll() {
      return ResponseEntity.ok(clientRepository.fetchAll());
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      clientRepository.deleteById(id);
      return ResponseEntity.ok().build();
   }
}
