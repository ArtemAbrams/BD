package com.labexample.Data;

import com.labexample.entities.Garbage;
import com.labexample.enums.Color;
import com.labexample.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductData {

    private String name;

    private double price;

    private Color color;

    private Type type;

    private double size;

    private Long amountOfProduct;

    private Garbage garbage;
}
