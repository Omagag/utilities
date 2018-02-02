package com.bbva.intranet.utilities.web.ws.utilities;

import com.bbva.intranet.utilities.web.ws.vos.ServiceResponse;

import javax.servlet.http.HttpServletResponse;

public class ServiceResponseMessageUtility {

    public static void setResponse(ServiceResponse serviceResponse, ServiceResponseMessage serviceResponseMessage) {
        serviceResponse.setData(null);
        switch (serviceResponseMessage) {
            // General Messages
            case SUCCESS:
                serviceResponse.setCode("00");
                serviceResponse.setMessage("Successful");
                break;
            case EMPTY_REQUEST_BODY:
                serviceResponse.setCode("02");
                serviceResponse.setMessage("The request body is empty.");
                break;
            case GENERIC_ERROR:
                serviceResponse.setCode("-01");
                serviceResponse.setMessage("Generic error has occurred, check logs for more information");
                serviceResponse.setHttpCode(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                serviceResponse.setHttpMessage("Service unavailable.");
                break;

            // DataBase Messages
            case DATA_DABE_ERROR:
                serviceResponse.setCode("-10");
                serviceResponse.setMessage("DataBase error, check logs for more information.");
                serviceResponse.setHttpCode(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                serviceResponse.setHttpMessage("Service unavailable.");
                break;
            case DATA_BASE_NO_DATA_FOUND:
                serviceResponse.setCode("-11");
                serviceResponse.setMessage("No data available.");
                break;

        }

    }
}
