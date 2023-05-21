package com.labexample.DTO;

import com.labexample.enums.Gender;
import com.labexample.enums.Transport;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class DeliveryDTO {
    private String name;
    private String nameProduct;
    private String mobilePhone;
    private Gender gender;
    private Transport transport;
}
