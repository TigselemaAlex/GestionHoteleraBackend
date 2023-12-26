package com.hotel.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.demo.models.dto.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
@Data

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "cedula_cliente", length = 10, nullable = false, unique = true)
    private String cedula;

    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "nombre_cliente", length = 20, nullable = false)
    private String nombre;

    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "apellido_cliente", length = 20, nullable = false)
    private String apellido;


    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "telefono_cliente", length = 10, nullable = false)
    private String telefono;




    //public void addReserva(Reserva reserva){
   //     listReserva.add(reserva);
   // }

   // public void removeReserva(Reserva reserva){
  //      listReserva.remove(reserva);
   // }

    public static Cliente from(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setTelefono(clienteDto.getTelefono());
        return cliente;
    }


}
