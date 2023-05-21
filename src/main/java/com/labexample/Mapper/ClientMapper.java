package com.labexample.Mapper;

import com.labexample.Data.ClientData;
import com.labexample.entities.Client;
import org.springframework.stereotype.Service;
import java.util.function.Function;


@Service
public class ClientMapper implements Function<ClientData, Client> {
    @Override
    public Client apply(ClientData clientData) {
        return new Client(clientData.getName(),
                clientData.getSurname(),
                clientData.getBirthday(),
                clientData.getMobilePhone(),
                clientData.getPassword(),
                clientData.getGender(),
                clientData.getOrders()
        );
    }
}
