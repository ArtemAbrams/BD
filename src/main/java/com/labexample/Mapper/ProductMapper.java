package com.labexample.Mapper;

import com.labexample.Data.OrderData;
import com.labexample.Data.ProductData;
import com.labexample.entities.Order;
import com.labexample.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductMapper implements Function<ProductData, Product> {
    @Override
    public Product apply(ProductData productData) {
        return new Product(productData.getName(),
                productData.getPrice(),
                productData.getColor(),
                productData.getType(),
                productData.getSize(),
                productData.getAmountOfProduct(),
                productData.getGarbage()
        );
    }
}
