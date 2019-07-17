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
        //String code = String.format("%s",codeError.getCode());
        String message;
        switch (codeError) {
            case GENERIC:
            case ENOUGH_DATA:
            case DATABASE:
                message = String.format("code.error.%s", codeError.getCode());
                break;
            default:
                message = String.format("code.error.%s.%s", prefix, codeError.getCode());
                break;
        }

        response = generateResponse(code,
                messageSource.getMessage(message, null, locale),
                e, httpStatusCode);
        return response;
    }

}
