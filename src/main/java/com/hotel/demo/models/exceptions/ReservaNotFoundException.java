package com.hotel.demo.models.exceptions;

import java.text.MessageFormat;

public class ReservaNotFoundException extends RuntimeException{

    public ReservaNotFoundException(final Long idReserva) {
        super(MessageFormat.format("No se encontró la reserva con el id: {0}", idReserva));
    }
}
