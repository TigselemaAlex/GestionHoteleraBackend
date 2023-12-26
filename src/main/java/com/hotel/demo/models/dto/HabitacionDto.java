package com.hotel.demo.models.dto;

import com.hotel.demo.models.entities.Habitacion;
import lombok.Data;

import javax.persistence.Column;

@Data

public class HabitacionDto {

    private Long id;
    private Integer numero;
    private Boolean estado;
    private String tipo;
    private Double precio;

    public static HabitacionDto from(Habitacion habitacion){
        HabitacionDto habitacionDto = new HabitacionDto();
        habitacionDto.setId(habitacion.getId());
        habitacionDto.setNumero(habitacion.getNumero());
        habitacionDto.setEstado(habitacion.getEstado());
        habitacionDto.setTipo(habitacion.getTipo());
        habitacionDto.setPrecio(habitacion.getPrecio());
        return habitacionDto;
    }

}
