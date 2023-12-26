package com.hotel.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.demo.models.dto.HabitacionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habitaciones")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

//id, numero, estado, tipo, pago y precio
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion", length = 15)
    private Long id;

    @Column(name = "numero_habitacion", length = 15, nullable = false, unique = true)
    private Integer numero;

    @Column(name = "estado_habitacion", nullable = false)
    private Boolean estado;

    @Column(name = "tipo_habitacion", length = 20, nullable = false)
    private String tipo;

    @Column(name = "precio_habitacion", length = 15, nullable = false)
    private Double precio;

    public static Habitacion from(HabitacionDto habitacionDto){
        Habitacion habitacion = new Habitacion();
        habitacion.setId(habitacionDto.getId());
        habitacion.setNumero(habitacionDto.getNumero());
        habitacion.setEstado(habitacionDto.getEstado());
        habitacion.setTipo(habitacionDto.getTipo());
        habitacion.setPrecio(habitacionDto.getPrecio());
        return habitacion;
    }

}
