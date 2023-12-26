package com.hotel.demo.repository;

import com.hotel.demo.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {



}
