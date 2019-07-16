package com.bbva.intranet.utilities.web.ws;

import com.bbva.intranet.utilities.web.ws.enums.CodeError;
import org.springframework.context.MessageSource;

import javax.ws.rs.core.Response;
import java.util.Locale;

public class GenericWsPlus extends GenericWs {

    public Response generateResponse(String prefix, CodeError codeError, Exception e, int httpStatusCode,
                                     MessageSource messageSource, Locale locale) {
        Response response;

        String code = String.format("%s-%s", prefix, codeError.getCode());
        String message = String.format("code.error.%s.%s", prefix, codeError.getCode());
        response = generateResponse(code,
                messageSource.getMessage(message, null, locale),
                e, httpStatusCode);

        return response;
    }

}
