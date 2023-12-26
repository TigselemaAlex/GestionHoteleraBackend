package com.hotel.demo.models.entities;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.demo.models.dto.ReservaDto;
import com.hotel.demo.repository.ClienteRepository;
import com.hotel.demo.services.ClienteService;
import com.hotel.demo.services.HabitacionService;
import com.hotel.demo.services.ReservaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", length = 15)
    private Long id;

    @Column(name = "fecha_entrada", nullable = false)
    private Date fechaentrada;

    @Column(name = "fecha_salida", nullable = false)
    private Date fechasalida;

    @Column(name = "pago_habitacion", nullable = false)
    private Boolean pagohabitacion;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    public Cliente cliente;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    public Habitacion habitacion;

    @PrePersist
    public void prePersist(){
        fechaentrada = new Date();
    }


    public static Reserva from(ReservaDto reservaDto){
        Reserva reserva = new Reserva();
        reserva.setId(reservaDto.getId());
        reserva.setFechaentrada(reservaDto.getFechaentrada());
        reserva.setFechasalida(reservaDto.getFechasalida());
        reserva.setPagohabitacion(reservaDto.getPagohabitacion());
        reserva.setCliente(ClienteService.getCliente(reservaDto.getCliente().longValue()));
        reserva.setHabitacion(HabitacionService.getHabitacion(reservaDto.getHabitacion().longValue()));
        return reserva;
    }
}
