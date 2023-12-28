package com.egg.apirestful.api_restful.services;

import com.egg.apirestful.api_restful.dto.ClienteDTO;
import com.egg.apirestful.api_restful.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository=clienteRepository;
    }

    @Transactional
    public void addCliente( ClienteDTO clientDTO){

    }
}
