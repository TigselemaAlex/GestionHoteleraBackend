package com.hotel.demo.models.exceptions;

import java.text.MessageFormat;

public class HabitacionNotFoundException extends RuntimeException{

    public HabitacionNotFoundException(final Long idHabitacion) {
        super(MessageFormat.format("No se encontro la habitación con el id: {0}", idHabitacion));
    }
}
