package com.bbva.intranet.utilities.web.ws;

import com.bbva.intranet.utilities.web.ws.vos.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by BBVA on 16/06/2016.
 * OGG
 */
public class BaseRestService {

    private static final Logger logger = LoggerFactory.getLogger(BaseRestService.class);

    protected ServiceResponse serviceResponse = new ServiceResponse();

    protected void printAndNotifyOfException(Exception e, HttpServletResponse response) {
        response.setStatus(501);
        this.serviceResponse = new ServiceResponse();
        this.serviceResponse.setCode("-1");
        this.serviceResponse.setMessage("An error has occurred.");
        this.serviceResponse.setHttpCode("503");
        this.serviceResponse.setHttpMessage("Service Unavailable.");
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        this.serviceResponse.setMessage(String.format("%s: [%s]", this.serviceResponse.getMessage(),
                e.getMessage() != null ? e.getMessage() : e.toString()));
//        return GsonUtility.objectToJson(serviceResponse);
    }

    protected void exceptionManagement(Exception e, HttpServletResponse response, ServiceResponse serviceResponse) {
        response.setStatus(503);
//        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setCode("-1");
        serviceResponse.setMessage("An error has occurred.");
        serviceResponse.setHttpCode("503");
        serviceResponse.setHttpMessage("Service Unavailable.");
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        serviceResponse.setMessage(String.format("%s: [%s]", serviceResponse.getMessage(),
                e.getMessage() != null ? e.getMessage() : e.toString()));
//        return GsonUtility.objectToJson(serviceResponse);
//        return serviceResponse;
    }

}
