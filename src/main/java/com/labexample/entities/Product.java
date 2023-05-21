package com.labexample.entities;

import com.labexample.enums.Color;
import com.labexample.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AbstractEntity{
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "colour", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Color color;
    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @Column(name = "size", nullable = false)
    private double size;
    @Column(name = "rating_score")
    private Long amountOfProduct;
    @ManyToOne(fetch = FetchType.LAZY)
    private Garbage garbage;
}
