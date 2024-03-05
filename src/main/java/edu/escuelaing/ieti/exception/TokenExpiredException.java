package edu.escuelaing.ieti.exception;

import org.springframework.web.server.ServerErrorException;import org.springframework.web.server.ServerErrorException;
import static edu.escuelaing.ieti.data.Constants.TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE;

public class TokenExpiredException extends ServerErrorException {

    public TokenExpiredException() {
        super(TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE);
    }

}
