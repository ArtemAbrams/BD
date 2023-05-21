package com.labexample.DTO;

import com.labexample.entities.Garbage;
import com.labexample.enums.Color;
import com.labexample.enums.Type;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDTO {
    private String name;

    private double price;

    private Color color;

    private Type type;

    private double size;

    private Long amountOfProduct;

}
