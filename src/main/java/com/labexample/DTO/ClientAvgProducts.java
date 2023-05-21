package com.labexample.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientAvgProducts {
    private String name;
    private String surname;
    private double avgPrice;

}
