package com.labexample.entities;

import com.labexample.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractEntity{
    @Column(name = "first_name", nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String surname;
    @Column(name = "birthday", nullable = false)
    private LocalDateTime birthday;
    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }
}
