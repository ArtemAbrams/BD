package com.labexample.DTO;

import com.labexample.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ClientDto {

    private String name;

    private String surname;

    private LocalDateTime birthday;

    private String mobilePhone;

    private Gender gender;
}
