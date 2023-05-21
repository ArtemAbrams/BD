package com.labexample.DTO;

import com.labexample.entities.Client;
import com.labexample.entities.Delivery;
import com.labexample.entities.Feedback;
import com.labexample.enums.Complete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    private String fromAddress;
    private String toAddress;
    private Complete complete;
}
