package com.hotel.demo.models.dto;

import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.entities.Reserva;
import lombok.Data;

import java.util.Date;

@Data

public class ResponseReservaDto {

    private Long id;
    private Date fechaentrada;
    private Date fechasalida;
    private Boolean pagohabitacion;
    public Cliente cliente;
    public Habitacion habitacion;

    public static ResponseReservaDto from(Reserva reserva){
        ResponseReservaDto responseReservaDto = new ResponseReservaDto();
        responseReservaDto.setId(reserva.getId());
        responseReservaDto.setFechaentrada(reserva.getFechaentrada());
        responseReservaDto.setFechasalida(reserva.getFechasalida());
        responseReservaDto.setPagohabitacion(reserva.getPagohabitacion());
        responseReservaDto.setCliente(reserva.getCliente());
        responseReservaDto.setHabitacion(reserva.getHabitacion());
        return responseReservaDto;
    }

}
