package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.ReservaDto;
import com.hotel.demo.models.dto.ResponseReservaDto;
import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.entities.Reserva;
import com.hotel.demo.services.HabitacionService;
import com.hotel.demo.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reservas")
//http://localhost:9095/hotel/reservas

public class ReservaController {

    private final ReservaService reservaService;
    private final HabitacionService habitacionService;

    @Autowired
    public ReservaController(ReservaService reservaService, HabitacionService habitacionService) {
        this.reservaService = reservaService;
        this.habitacionService = habitacionService;
    }

    @Operation(summary = "Registration of a new reservation in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) })
    })

    @PostMapping
    public ResponseEntity<?> addReserva(@RequestBody final ReservaDto reservaDto){
        if(habitacionService.checkHabitacion(reservaDto.getHabitacion())){
            Reserva reserva = reservaService.addReserva(Reserva.from(reservaDto));
            Habitacion habitacion = habitacionService.changeEstadoHabitacion(reservaDto.getHabitacion());
            return new ResponseEntity<>(ResponseReservaDto.from(reserva), HttpStatus.OK);
        }
        return new ResponseEntity<>("La habitación actualmente está ocupada", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get the list of all reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) })
    })

    @GetMapping
    public ResponseEntity<List<ResponseReservaDto>> getReservas(){
        List<Reserva> reservaList = reservaService.getReservas();
        List<ResponseReservaDto> reservaDtoList = reservaList.stream().map(ResponseReservaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(reservaDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Get one Reservation by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) })
    })

    @GetMapping(value = "/{idreserva}")
    public ResponseEntity<ResponseReservaDto> getReserva(@PathVariable final Long idreserva){
        Reserva reserva = reservaService.getReserva(idreserva);
        return new ResponseEntity<>(ResponseReservaDto.from(reserva), HttpStatus.OK);
    }

    @Operation(summary = "Delete one Reservation by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) })
    })

    @DeleteMapping(value = "/{idreserva}")
    public ResponseEntity<ResponseReservaDto> deleteReserva(@PathVariable final Long idreserva){
        Reserva reserva = reservaService.deleteReserva(idreserva);
        return new ResponseEntity<>(ResponseReservaDto.from(reserva), HttpStatus.OK);
    }

    @Operation(summary = "Update one Reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) })
    })

    @PutMapping(value = "/{idreserva}")
    public ResponseEntity<ResponseReservaDto> updateReserva(@PathVariable final Long idreserva, @RequestBody final ReservaDto reservaDto){
        Reserva reserva = reservaService.updateReserva(idreserva, Reserva.from(reservaDto));
        return new ResponseEntity<>(ResponseReservaDto.from(reserva), HttpStatus.OK);
    }

}
