package com.labexample.Data;

import com.labexample.entities.Client;
import com.labexample.entities.Delivery;
import com.labexample.entities.Feedback;
import com.labexample.entities.Product;
import com.labexample.enums.Complete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class OrderData {
    private String fromAddress;
    private String toAddress;
    private Client client;
    private List<Feedback> feedbacks = new ArrayList<>();
    private Delivery delivery;
    private Complete complete;
    private List<Product> products = new ArrayList<>();
}
