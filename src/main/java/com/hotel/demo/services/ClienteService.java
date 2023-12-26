package com.hotel.demo.services;

import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Reserva;
import com.hotel.demo.models.exceptions.ClienteNotFoundException;
import com.hotel.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional

public class ClienteService {
    private static ClienteRepository clienteRepository;
    private final ReservaService reservaService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ReservaService reservaService) {

        this.clienteRepository = clienteRepository;
        this.reservaService = reservaService;
    }

    //agregar Cliente
    public Cliente addCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //obtener lista de clientes
    public List<Cliente> getClientes(){
        return StreamSupport.stream(clienteRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //obtener un cliente por id
    public static Cliente getCliente(Long idcliente){
        return clienteRepository.findById(idcliente).orElseThrow(() ->
                new ClienteNotFoundException(idcliente));
    }

    //eliminar un cliente
    public Cliente deleteCliente(Long idcliente){
        Cliente cliente = getCliente(idcliente);
        clienteRepository.delete(cliente);
        return cliente;
    }

    public Cliente updateCliente(Long idcliente, Cliente cliente){
        Cliente clienteToEdit = getCliente(idcliente);
        clienteToEdit.setNombre(cliente.getNombre());
        clienteToEdit.setApellido(cliente.getApellido());
        clienteToEdit.setTelefono(cliente.getTelefono());
        return clienteToEdit;
    }


}
