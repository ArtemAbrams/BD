package com.labexample.entities;

import com.labexample.enums.Gender;
import com.labexample.enums.Transport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends AbstractEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column(name = "transport")
    @Enumerated(value = EnumType.STRING)
    private Transport transport;
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
