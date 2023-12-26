package com.hotel.demo.models.exceptions;

import java.text.MessageFormat;

public class ClienteNotFoundException extends RuntimeException{

    public ClienteNotFoundException(final Long idlciente){
        super(MessageFormat.format("No se encontro el cliente, con el id: {0}", idlciente));
    }

}
