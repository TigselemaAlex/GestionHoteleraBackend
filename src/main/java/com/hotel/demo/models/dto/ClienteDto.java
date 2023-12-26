package com.hotel.demo.models.dto;

import com.hotel.demo.models.entities.Cliente;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data

public class ClienteDto {

    private Long id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;

    public static ClienteDto from(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }

}
