package com.gft.testapi.config;

import java.time.LocalDate;
import com.gft.testapi.entities.dto.ClientDTO;
import com.gft.testapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClientService clientService;

    @Override
    public void run(String... args) throws Exception {

        var c1 = new ClientDTO("Joao", 22, LocalDate.now());
        var c2 = new ClientDTO("Maria", 30, LocalDate.of(1992, 11, 21));
        var c3 = new ClientDTO("Jaddy", 19, LocalDate.of(2015, 04, 19));
        var c4 = new ClientDTO("Jéssica", 44, LocalDate.of(1991, 9, 9));
        var c5 = new ClientDTO("Cléber", 62, LocalDate.of(1983, 07, 03));

        clientService.save(c1);
        clientService.save(c2);
        clientService.save(c3);
        clientService.save(c4);
        clientService.save(c5);

    }

}
