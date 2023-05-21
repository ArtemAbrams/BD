package com.labexample.Mapper;

import com.labexample.Data.ClientData;
import com.labexample.Data.OrderData;
import com.labexample.entities.Client;
import com.labexample.entities.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderMapper implements Function<OrderData, Order> {
    @Override
    public Order apply(OrderData orderData) {
        return new Order(orderData.getFromAddress(),
                orderData.getToAddress(),
                orderData.getClient(),
                orderData.getFeedbacks(),
                orderData.getDelivery(),
                orderData.getComplete(),
                orderData.getProducts()
        );
    }
}
