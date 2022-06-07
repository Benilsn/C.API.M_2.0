package com.gft.testapi.services;

import java.util.List;
import java.util.stream.Collectors;
import com.gft.testapi.entities.dto.ClientDTO;
import com.gft.testapi.entities.models.Client;
import com.gft.testapi.exceptions.ClientNotFoundException;
import com.gft.testapi.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public static final String CLIENT_NOT_FOUND = "Client not found!";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper mapper;

    public List<ClientDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(c -> mapper.map(c, ClientDTO.class))
                .collect(Collectors.toList());
    }

    public Client getbyId(Long id) {
        var obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientDTO save(ClientDTO clientDTO) {
        clientRepository.save(mapper.map(clientDTO, Client.class));
        return clientDTO;
    }

    public Client delete(Long id) throws ClientNotFoundException {

        var client = clientRepository.findById(id);

        client.ifPresent(value -> clientRepository.delete(value));

        return client.orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
    }

    public Client update(ClientDTO clientDTO) throws ClientNotFoundException {

        var client = clientRepository.findById(clientDTO.getId());
        var updatedClient = new Client();

        if (client.isPresent()) {
            updatedClient = client.get().updateName(clientDTO.getName())
                    .updateAge(clientDTO.getAge())
                    .updateDate(clientDTO.getMemberSince());
            return clientRepository.save(updatedClient);
        } else {
            throw new ClientNotFoundException(CLIENT_NOT_FOUND);
        }

    }

}
