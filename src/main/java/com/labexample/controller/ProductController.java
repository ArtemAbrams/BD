package com.labexample.controller;

import com.labexample.DTO.ProductDTO;
import com.labexample.Data.ProductData;
import com.labexample.Exception.OrderNotFound;
import com.labexample.Exception.ProductNotFoundException;
import com.labexample.Mapper.ProductMapper;
import com.labexample.entities.Product;
import com.labexample.repository.OrderRepository;
import com.labexample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;
    @PostMapping("/create")
    public ResponseEntity<Product> createTrain(@RequestBody @Valid ProductData productData) {
        return ResponseEntity.ok(productRepository.save(productMapper.apply(productData)));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateTrain(@RequestParam Long id,
                                             @RequestBody @Valid ProductData productData) {
        final var productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id +" not found!"));
        productToUpdate.setName(productData.getName());
        productToUpdate.setSize(productData.getSize());
        productToUpdate.setColor(productData.getColor());
        productToUpdate.setPrice(productData.getPrice());
        productToUpdate.setType(productData.getType());
        productRepository.save(productToUpdate);
        return ResponseEntity.ok(productToUpdate);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> getTrain(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.fetchById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id +" not found!")));
    }
    @PutMapping("/joinProduct")
    public ResponseEntity<String> newProduct(@RequestParam Long orderId, @RequestParam Long productId){
          var order = orderRepository.findById(orderId)
                  .orElseThrow(()-> new OrderNotFound("Order with: " + orderId + " Not Found"));
          var product = productRepository.findById(productId)
                  .orElseThrow(() -> new ProductNotFoundException("Product with: " + productId + " Not Found"));
          var listProducts = order.getProducts();
          listProducts.add(product);
          order.setProducts(listProducts);
          return ResponseEntity.ok("You add the product");
    }
    @GetMapping("/get")
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productRepository.fetchAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
