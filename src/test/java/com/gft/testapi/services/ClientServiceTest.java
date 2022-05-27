package com.gft.testapi.services;

import com.gft.testapi.entities.dto.ClientDTO;
import com.gft.testapi.entities.models.Client;
import com.gft.testapi.exceptions.ClientNotFoundException;
import com.gft.testapi.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ClientServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Benilson";
    public static final int AGE = 24;
    public static final LocalDate MEMBER_SINCE = LocalDate.now();
    public static final String CLIENT_NOT_FOUND = "Client not found!";


    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper mapper;

    private Client client;

    private ClientDTO clientDTO;

    private Optional<Client> optionalClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startClients();
    }

    @Test
    void getAll() {

    }

    @Test
    void when_find_by_id_return_user_instance() {
        when(clientRepository.findById(anyLong()))
                                     .thenReturn(optionalClient);

        var response = clientService.getbyId(ID);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(MEMBER_SINCE, response.getMemberSince());
    }

    @Test
    void when_dont_find_user_throw_exception(){
        when(clientRepository.findById(anyLong()))
                             .thenThrow(new ClientNotFoundException(CLIENT_NOT_FOUND));

        try{
            clientService.getbyId(ID);
        }catch (Exception e){
            assertEquals(ClientNotFoundException.class, e.getClass());
            assertEquals(CLIENT_NOT_FOUND, e.getMessage());
        }

    }

    @Test
    void when_find_all_return_a_list_of_clients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        var list = clientRepository.findAll();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(Client.class, list.get(0).getClass());
    }

    @Test
    void when_delete_client() {
    }

    @Test
    void update() {
    }

    @Test
    void clientExists() {
    }

    private void startClients(){
        client = new Client(ID, NAME, AGE, MEMBER_SINCE);
        clientDTO = new ClientDTO(ID,NAME, AGE, MEMBER_SINCE);
        optionalClient = Optional.of(new Client(ID,NAME, AGE, MEMBER_SINCE));

    }
}