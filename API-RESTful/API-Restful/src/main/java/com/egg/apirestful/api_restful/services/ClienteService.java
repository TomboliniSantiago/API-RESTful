package com.egg.apirestful.api_restful.services;

import com.egg.apirestful.api_restful.dto.ClienteDTO;
import com.egg.apirestful.api_restful.entities.Cliente;
import com.egg.apirestful.api_restful.exceptions.MyException;
import com.egg.apirestful.api_restful.mappers.ClienteMapper;
import com.egg.apirestful.api_restful.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository=clienteRepository;
        this.clienteMapper=clienteMapper;
    }

    public List<ClienteDTO> getAllClients(){
        List<Cliente> clientes  = this.clienteRepository.findAll();
        return clienteMapper.convertToDtoList(clientes);

    }

    public ClienteDTO getClienteById(String id) throws MyException {
        Cliente cliente = clienteRepository.getById(id);
         if (cliente.getId()!=null){
             return clienteMapper.convertToDto(cliente);
         }else{

             throw new MyException("No existe un cliente con el ID: "+id);
         }
    }

    @Transactional
    public void updateCliente(String id, ClienteDTO clienteDTOupdated) throws MyException{
        Cliente cliente = clienteRepository.getById(id);

        if (cliente.getId()!=null){
            cliente.setEmail(clienteDTOupdated.getEmail());
            cliente.setTelefono(clienteDTOupdated.getTelefono());
            cliente.setNombre(clienteDTOupdated.getName());
            clienteRepository.save(cliente);
        }else throw new MyException("No existe un cliente con el ID: " + id);

    }

    public ClienteDTO deactivateClient(String id) throws MyException {
        Cliente cliente = clienteRepository.getById(id);

        if (cliente.getId()!=null){
            cliente.setActive(false);
            this.clienteRepository.save(cliente);
            return clienteMapper.convertToDto(cliente);
        }else {
            throw new MyException("Couldn't deactivate the user");
        }
    };
    @Transactional
    public ClienteDTO addCliente(ClienteDTO clienteDTO) throws MyException{

        validate(clienteDTO);
        Cliente cliente = clienteMapper.convertToEntity(clienteDTO);
        this.clienteRepository.save(cliente);
        return clienteMapper.convertToDto(cliente);
    }

    private void validate(ClienteDTO clienteDTO) throws MyException {
        if(clienteDTO.getEmail().isEmpty()){
            throw new MyException("El email no puede estar vacio");
        }
        if(clienteDTO.getTelefono().isEmpty()){
            throw new MyException("El teléfono no puede estar vacio");
        }
        if(clienteDTO.getName().isEmpty()){
            throw new MyException("El nombre no puede estar vacio");
        }
    }


}
