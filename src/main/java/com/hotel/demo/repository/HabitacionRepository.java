package com.hotel.demo.repository;

import com.hotel.demo.models.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
