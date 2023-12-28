package com.egg.apirestful.api_restful.mappers;
import com.egg.apirestful.api_restful.dto.ClienteDTO;
import com.egg.apirestful.api_restful.entities.Cliente;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper{
    private final ModelMapper modelMapper;


    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteDTO convertToDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente convertToEntity(ClienteDTO clientDto) {
        return modelMapper.map(clientDto, Cliente.class);
    }

    public List<ClienteDTO> convertToDtoList(List<Cliente> clients) {
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}