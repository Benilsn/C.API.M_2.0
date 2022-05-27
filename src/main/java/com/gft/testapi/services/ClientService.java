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

    public ClientDTO getbyId(Long id) {

        if (clientExists(id)) {
            var client = clientRepository.findById(id).get();
            return mapper.map(client, ClientDTO.class);
        } else {
            throw new ClientNotFoundException("Client not found!");
        }

    }

    public ClientDTO save(ClientDTO clientDTO) {
        clientRepository.save(mapper.map(clientDTO, Client.class));
        return clientDTO;
    }

    public void delete(Long id) throws ClientNotFoundException {

        var client = clientRepository.findById(id);

        if (clientExists(id)) {
            clientRepository.delete(client.get());
        } else {
            throw new ClientNotFoundException("Client Not Found!");
        }
    }

    public ClientDTO update(Long id, ClientDTO clientDTO) throws ClientNotFoundException {

        var client = clientRepository.findById(id);

        if (clientExists(id)) {

            var updatedClient = client.get().updateName(clientDTO.getName())
                    .updateAge(clientDTO.getAge())
                    .updateDate(clientDTO.getMemberSince());

            clientRepository.save(updatedClient);
        } else {
            throw new ClientNotFoundException("Client Not Found!");
        }
        return mapper.map(client.get(), ClientDTO.class);
    }

    public boolean clientExists(Long id) {

        var client = clientRepository.findById(id);

        if (client.isPresent()) {
            return true;
        } else {
            return false;
        }

    }

}
