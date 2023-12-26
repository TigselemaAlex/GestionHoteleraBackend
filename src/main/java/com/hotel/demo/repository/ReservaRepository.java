package com.hotel.demo.repository;

import com.hotel.demo.models.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
