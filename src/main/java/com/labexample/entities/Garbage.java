package com.labexample.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "garbages")
public class Garbage extends AbstractEntity {
    @Column(name = "square", nullable = false)
    private double square;
    @Column(name = "address_garbage")
    private String address;

    @OneToMany(mappedBy = "garbage", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Product> products = new ArrayList<>();
}
