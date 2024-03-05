package edu.escuelaing.ieti.exception;

import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {

    public InvalidCredentialsException() {
        super();
    }
}

