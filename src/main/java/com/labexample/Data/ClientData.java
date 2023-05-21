package com.labexample.Data;

import com.labexample.entities.Order;
import com.labexample.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ClientData {

    private String name;

    private String surname;

    private LocalDateTime birthday;

    private String mobilePhone;

    private String password;

    private Gender gender;

    private List<Order> orders = new ArrayList<>();
}
