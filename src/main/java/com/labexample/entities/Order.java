package com.labexample.entities;

import com.labexample.enums.Complete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity{
    @Column(name = "from_address", nullable = false)
    private String fromAddress;
    @Column(name = "to_address", nullable = false)
    private String toAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Delivery delivery;
    @Column(name = "completed")
    @Enumerated(value = EnumType.STRING)
    private Complete complete;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
}
