package com.bbva.intranet.utilities.web.ws.utilities;

import com.bbva.intranet.utilities.web.ws.vos.ServiceResponse;

import javax.servlet.http.HttpServletResponse;

public class ServiceResponseMessageUtility {

    public static void setResponse(ServiceResponse serviceResponse, String message) {
        serviceResponse.setData(null);
        serviceResponse.setHttpCode(HttpServletResponse.SC_OK);
        serviceResponse.setHttpMessage(null);
        serviceResponse.setCode("-01");
        serviceResponse.setMessage(message);
    }

    public static void setResponse(ServiceResponse serviceResponse, ServiceResponseMessage serviceResponseMessage) {
        serviceResponse.setData(null);
        serviceResponse.setHttpCode(null);
        serviceResponse.setHttpMessage(null);
        switch (serviceResponseMessage) {
            // General Messages
            case SUCCESS:
                serviceResponse.setCode("00");
                serviceResponse.setMessage("Successful");
                break;
            case GENERIC_ERROR:
                serviceResponse.setCode("-01");
                serviceResponse.setMessage("An error has occurred, check logs for more information.");
                serviceResponse.setHttpCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                serviceResponse.setHttpMessage("Service unavailable.");
                break;
            case EMPTY_REQUEST_BODY:
                serviceResponse.setCode("-02");
                serviceResponse.setMessage("The request body is empty.");
                serviceResponse.setHttpCode(HttpServletResponse.SC_OK);
                break;
            case JSON_SYNTAX_ERROR:
                serviceResponse.setCode("-03");
                serviceResponse.setMessage("JSON Syntax error.");
                serviceResponse.setHttpCode(HttpServletResponse.SC_OK);
                break;
                // DataBase Messages
            case DATA_BASE_ERROR:
                serviceResponse.setCode("-10");
                serviceResponse.setMessage("DataBase error, check logs for more information.");
                serviceResponse.setHttpCode(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                serviceResponse.setHttpMessage("Service unavailable.");
                break;
            case DATA_BASE_NO_DATA_FOUND:
                serviceResponse.setCode("-11");
                serviceResponse.setMessage("No data available.");
                serviceResponse.setHttpCode(HttpServletResponse.SC_NOT_FOUND);
                break;

        }

    }
}
