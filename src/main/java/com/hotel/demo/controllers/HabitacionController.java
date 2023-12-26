package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.HabitacionDto;
import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.services.HabitacionService;
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
@RequestMapping(value = "/habitaciones")
//http://localhost:9095/hotel/habitaciones

public class HabitacionController {

    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @Operation(summary = "Registration of a new room in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "500", description = "Invalid data in the form",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @PostMapping
    public ResponseEntity<HabitacionDto> addHabitacion(@RequestBody final HabitacionDto habitacionDto){
        Habitacion habitacion = habitacionService.addHabitacion(Habitacion.from(habitacionDto));
        return new ResponseEntity<>(HabitacionDto.from(habitacion), HttpStatus.OK);
    }

    @Operation(summary = "Get the list of all rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @GetMapping
    public ResponseEntity<List<HabitacionDto>> getHabitaciones(){
        List<Habitacion> habitaciones = habitacionService.getHabitaciones();
        List<HabitacionDto> habitacionDtos = habitaciones.stream().map(HabitacionDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(habitacionDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get one Room by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @GetMapping(value = "/{idhabitacion}")
    public ResponseEntity<HabitacionDto> getHabitacion(@PathVariable final Long idhabitacion){
        Habitacion habitacion = habitacionService.getHabitacion(idhabitacion);
        return new ResponseEntity<>(HabitacionDto.from(habitacion), HttpStatus.OK);
    }

    @Operation(summary = "Delete one Room by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @DeleteMapping(value = "/{idhabitacion}")
    public ResponseEntity<HabitacionDto> deleteHabitacion(@PathVariable final Long idhabitacion){
        Habitacion habitacion = habitacionService.deleteHabitacion(idhabitacion);
        return new ResponseEntity<>(HabitacionDto.from(habitacion), HttpStatus.OK);
    }

    @Operation(summary = "Update one Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @PutMapping(value = "/{idhabitacion}")
    public ResponseEntity<HabitacionDto> updateHabitacion(@PathVariable final Long idhabitacion, @RequestBody final HabitacionDto habitacionDto){
        Habitacion habitacion = habitacionService.updateHabitacion(idhabitacion, Habitacion.from(habitacionDto));
        return new ResponseEntity<>(HabitacionDto.from(habitacion),HttpStatus.OK);
    }

}
