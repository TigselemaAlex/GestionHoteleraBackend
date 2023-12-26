package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.ClienteDto;
import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.services.ClienteService;
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
@RequestMapping(value="/clientes")
//http://localhost:9095/hotel/clientes

public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Registration of a new client in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "500", description = "Invalid data in the form",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @PostMapping
    public ResponseEntity<ClienteDto> addCliente(@RequestBody final ClienteDto clienteDto){
        Cliente cliente = clienteService.addCliente(Cliente.from(clienteDto));
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

    @Operation(summary = "Get the list of all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getClientes(){
        List<Cliente> clientes = clienteService.getClientes();
        List<ClienteDto> clientesDto = clientes.stream().map(ClienteDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(clientesDto, HttpStatus.OK);
    }

    @Operation(summary = "Get one Customer by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @GetMapping(value = "/{idcliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable final Long idcliente){
        Cliente cliente = clienteService.getCliente(idcliente);
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

    @Operation(summary = "Delete one Customer by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @DeleteMapping(value = "/{idcliente}")
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable final Long idcliente){
        Cliente cliente = clienteService.deleteCliente(idcliente);
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

    @Operation(summary = "Update one Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @PutMapping(value = "/{idcliente}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable final Long idcliente, @RequestBody final ClienteDto clienteDto){
        Cliente cliente = clienteService.updateCliente(idcliente, Cliente.from(clienteDto));
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

}
