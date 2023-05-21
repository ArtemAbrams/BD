package com.labexample.repository;

import com.labexample.DTO.OrderDTO;
import com.labexample.DTO.ProductDTO;
import com.labexample.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new com.labexample.DTO.ProductDTO(c.name, c.price, c.color,c.type, c.size, c.amountOfProduct)" +
            " from Product c where c.Id = :id")
    Optional<ProductDTO> fetchById(Long id);
    @Query("select new com.labexample.DTO.ProductDTO(c.name, c.price, c.color,c.type, c.size, c.amountOfProduct)" +
            " from Product c")
    List<ProductDTO> fetchAll();
}
