package com.bbva.intranet.utilities.web.ws;

import com.bbva.intranet.utilities.web.ws.enums.ResponseCode;
import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.GsonBuilder;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.logging.Level;

public class GenericWsPlus extends GenericWs {

    protected Response generateResponse(String prefix, ResponseCode responseCode, Object obj, String dateFormat,
                                        int httpStatusCode, MessageSource messageSource, Locale locale) {
        if (prefix == null || prefix.isEmpty() || responseCode == null) {
            throw new IllegalArgumentException("Prefix or responseCode are empty.");
        }
        String code = String.format("%s-%s", prefix, responseCode.getCode());

        String message;
        switch (responseCode) {
            case SUCCESSFUL:
                message = "code.successful";
                break;
            case UNAUTHORIZED:
                message = "code.unauthorized";
                break;
            case GENERIC:
            case ENOUGH_DATA:
            case DATABASE:
                message = String.format("code.error.%s", responseCode.getCode());
                break;
            default:
                message = String.format("code.error.%s.%s", prefix, responseCode.getCode());
                break;
        }

        return buildResponse(code, message, obj, dateFormat, httpStatusCode, messageSource, locale);
    }

    protected Response generateResponse(String prefix, String responseCode, Object obj, String dateFormat,
                                        int httpStatusCode, MessageSource messageSource, Locale locale) {
        if (prefix == null || prefix.isEmpty() || responseCode == null || responseCode.isEmpty()) {
            throw new IllegalArgumentException("Prefix or responseCode are empty.");
        }
        String code = String.format("%s-%s", prefix, responseCode);
        String message = String.format("code.error.%s.%s", prefix, responseCode);

        return buildResponse(code, message, obj, dateFormat, httpStatusCode, messageSource, locale);
    }

    protected Response generateResponse(String prefix, ResponseCode responseCode, Object obj, int httpStatusCode,
                                        MessageSource messageSource, Locale locale) {
        return generateResponse(prefix, responseCode, obj, null, httpStatusCode, messageSource, locale);
    }

    protected Response generateResponse(String prefix, String responseCode, Object obj, int httpStatusCode,
                                        MessageSource messageSource, Locale locale) {
        return generateResponse(prefix, responseCode, obj, null, httpStatusCode, messageSource, locale);
    }

    private Response buildResponse(String code, String message, Object obj, String dateFormat,
                                        int httpStatusCode, MessageSource messageSource, Locale locale) {
        Response response;

        if (dateFormat != null && !dateFormat.isEmpty()) {
            gson = new GsonBuilder().setDateFormat(dateFormat).create();
        }

        if (obj instanceof Exception) {
            Exception e = (Exception) obj;
            LOGGER.log(Level.SEVERE, e.toString(), e);
            message = e.getMessage() != null ? e.getMessage() : e.toString();
            response = generateResponse(code,
                    message,
                    obj, httpStatusCode);
        } else {
            response = generateResponse(code,
                    messageSource.getMessage(message, null, locale),
                    obj, httpStatusCode);
        }

        LOGGER.info(String.format("code: %s message: %s", code, message));

        return response;
    }

    protected Response validateRequestor(HttpServletRequest request, String wsKey, MessageSource messageSource, Locale locale) {
        Response response = null;
        String requestor = request.getHeader("requestor");
        if (requestor == null || requestor.isEmpty()) {
            response = generateResponse(wsKey, ResponseCode.UNAUTHORIZED, null,
                    HttpStatusCodes.STATUS_CODE_UNAUTHORIZED, messageSource, locale);
        }
        return response;
    }

    @Deprecated
    protected Response validateRequestor(String requestor, String wsKey, MessageSource messageSource, Locale locale) {
        Response response = null;

        if (requestor == null || requestor.isEmpty()) {
            response = generateResponse(wsKey, ResponseCode.SUCCESSFUL, null,
                    HttpStatusCodes.STATUS_CODE_BAD_REQUEST, messageSource, locale);
        }
        return response;
    }

    protected String getRequestor(HttpServletRequest request) {
        return request.getHeader("requestor");
    }

}
