package com.egg.apirestful.api_restful.controllers;

import com.egg.apirestful.api_restful.dto.ClienteDTO;
import com.egg.apirestful.api_restful.exceptions.MyException;
import com.egg.apirestful.api_restful.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ClienteController {

    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addClient(@Validated @RequestBody ClienteDTO clienteDTO) throws MyException {

        clienteService.addCliente(clienteDTO);
        return new ResponseEntity<>("Client added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<String> updateClient(@PathVariable String clientId, @Validated @RequestBody ClienteDTO updatedClienteDTO) throws MyException {
        clienteService.updateCliente(clientId, updatedClienteDTO);
        return new ResponseEntity<>("Client updated successfully", HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> getAllClients() {
        List<ClienteDTO> clients = clienteService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id_client}")
    public ResponseEntity<ClienteDTO> deactivateClient(@PathVariable String id_client) throws MyException {

        ClienteDTO clienteDTO = clienteService.getClienteById(id_client);
        if (clienteDTO != null) {
            ClienteDTO deactivatedClientDTO = clienteService.deactivateClient(id_client);
            return ResponseEntity.status(HttpStatus.OK).body(deactivatedClientDTO);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(null);
        }
    }
}
