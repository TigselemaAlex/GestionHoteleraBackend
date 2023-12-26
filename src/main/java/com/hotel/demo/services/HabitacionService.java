package com.hotel.demo.services;

import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.exceptions.HabitacionNotFoundException;
import com.hotel.demo.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional

public class HabitacionService {

    private static HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository){
        this.habitacionRepository = habitacionRepository;
    }

    //agregar habitacion
    public Habitacion addHabitacion(Habitacion habitacion){
        return habitacionRepository.save(habitacion);
    }

    //obtener lista de habitaciones
    public List<Habitacion> getHabitaciones(){
        return StreamSupport.stream(habitacionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //obtener una habitacion por id
    public static Habitacion getHabitacion(Long idHabitacion){
        return habitacionRepository.findById(idHabitacion).orElseThrow(() ->
                new HabitacionNotFoundException(idHabitacion));
    }

    //eliminar una habitacion
    public Habitacion deleteHabitacion(Long idHabitacion){
        Habitacion habitacion = getHabitacion(idHabitacion);
        habitacionRepository.delete(habitacion);
        return habitacion;
    }

    //editar datos de habitacion
    public Habitacion updateHabitacion(Long idHabitacion, Habitacion habitacion){
        Habitacion habitacionToEdit = getHabitacion(idHabitacion);
        habitacionToEdit.setNumero(habitacion.getNumero());
        habitacionToEdit.setTipo(habitacion.getTipo());
        habitacionToEdit.setPrecio(habitacion.getPrecio());
        habitacionToEdit.setEstado(habitacion.getEstado());
        return habitacionToEdit;
    }

    //cambiar estado de la habitacion
    public Habitacion changeEstadoHabitacion(Long idhabitacion){
        Habitacion habitacion = getHabitacion(idhabitacion);
        habitacion.setEstado(!habitacion.getEstado());
        return habitacion;
    }

    //verificar estado de la habitacion
    public Boolean checkHabitacion(Long idhabitacion){
        Habitacion habitacion = getHabitacion(idhabitacion);
        return habitacion.getEstado();
    }

}
