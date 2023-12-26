package com.hotel.demo.services;

import com.hotel.demo.models.entities.Reserva;
import com.hotel.demo.models.exceptions.ReservaNotFoundException;
import com.hotel.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional

public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionService habitacionService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, HabitacionService habitacionService) {
        this.reservaRepository = reservaRepository;
        this.habitacionService = habitacionService;
    }

    //Agregar reserva
    public Reserva addReserva(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    //obtener lista de reservas
    public List<Reserva> getReservas(){
        return StreamSupport.stream(reservaRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //obtener reserva por id
    public Reserva getReserva(Long idReserva){
        return reservaRepository.findById(idReserva).orElseThrow(() ->
                new ReservaNotFoundException(idReserva));
    }

    //eliminar reserva
    public Reserva deleteReserva(Long idReserva){
        Reserva reserva = getReserva(idReserva);
        reservaRepository.delete(reserva);
        habitacionService.changeEstadoHabitacion(reserva.getHabitacion().getId());
        return reserva;
    }

    //actualizar reserva
    public Reserva updateReserva(Long idReserva, Reserva reserva){

        Reserva reservaToEdit = getReserva(idReserva);
        habitacionService.changeEstadoHabitacion(reservaToEdit.getHabitacion().getId());
        reservaToEdit.setFechasalida(reserva.getFechasalida());
        reservaToEdit.setFechaentrada(reserva.getFechaentrada());
        reservaToEdit.setPagohabitacion(reserva.getPagohabitacion());
        reservaToEdit.setCliente(reserva.getCliente());
        reservaToEdit.setHabitacion(reserva.getHabitacion());
        habitacionService.changeEstadoHabitacion(reserva.getHabitacion().getId());
        return reservaToEdit;
    }

}
