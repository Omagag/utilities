package com.bbva.intranet.utilities.web.ws;

import com.bbva.intranet.utilities.web.ws.utilities.ServiceResponseMessage;
import com.bbva.intranet.utilities.web.ws.utilities.ServiceResponseMessageUtility;
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

//    protected void printAndNotifyOfException(Exception e, HttpServletResponse response) {
//        if (this.serviceResponse.getHttpCode() != null) {
//            response.setStatus(this.serviceResponse.getHttpCode());
//        } else {
//            ServiceResponseMessageUtility.setResponse(serviceResponse, ServiceResponseMessage.GENERIC_ERROR);
//            response.setStatus(this.serviceResponse.getHttpCode());
//        }
//
//        e.printStackTrace();
//        logger.error(e.getMessage(), e);
//        this.serviceResponse.setMessage(String.format("%s: Exception message=[%s]", this.serviceResponse.getMessage(),
//                e.getMessage() != null ? e.getMessage() : e.toString()));
//    }

    protected void printAndNotifyOfException(Exception e, HttpServletResponse response, ServiceResponseMessage serviceResponseMessage) {
//        if (this.serviceResponse.getHttpCode() != null) {
//            response.setStatus(this.serviceResponse.getHttpCode());
//        } else {
            ServiceResponseMessageUtility.setResponse(serviceResponse, serviceResponseMessage);
            response.setStatus(this.serviceResponse.getHttpCode());
//        }

        e.printStackTrace();
        logger.error(e.getMessage(), e);
        this.serviceResponse.setMessage(String.format("%s: Exception message=[%s]", this.serviceResponse.getMessage(),
                e.getMessage() != null ? e.getMessage() : e.toString()));
    }


//    protected void exceptionManagement(Exception e, HttpServletResponse response, ServiceResponse serviceResponse) {
//        if (serviceResponse.getHttpCode() != null) {
//            response.setStatus(serviceResponse.getHttpCode());
//        }
//        e.printStackTrace();
//        logger.error(e.getMessage(), e);
//        serviceResponse.setMessage(String.format("%s: [%s]", serviceResponse.getMessage(),
//                e.getMessage() != null ? e.getMessage() : e.toString()));
//    }

}
