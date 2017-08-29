package com.bbva.intranet.utilities;

import com.bbva.intranet.utilities.exceptions.RestServiceException;
import com.bbva.intranet.utilities.vos.HttpClientData;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

@Component
public abstract class HttpClientUtility implements Serializable {

	private static final long serialVersionUID = -1576248477031982672L;

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtility.class);

//	private final static String FILTER = "filter";
//	private final static String CATALOGO_ID = "catalog.id";

//    public static GetMethod buildAsoRequestGet(HttpServletRequest request, HttpClientData httpClientData) throws RestServiceException {
//        return buildRequestGet(request, httpClientData, true);
//    }

    public static GetMethod buildRequestGet(HttpServletRequest request, HttpClientData httpClientData) throws RestServiceException {
        GetMethod method = new GetMethod();
        try {
            String url = httpClientData.getUrl(); //URL del granting ticket

            logger.info(String.format("GET Request URL: [%s]", url));

            method.setURI(new URI(url, false));

            return method;
        } catch (URIException e) {
//            e.printStackTrace();
            logger.error(String.format("A error has occurred at the moment of invoke the services [%s].", method.getPath()));
            throw new RestServiceException(e.getMessage());
        }
    }

//    public static PostMethod buildAsoRequestPost(HttpServletRequest request, HttpClientData httpClientData) throws RestServiceException {
//        return buildRequestPost(request, httpClientData, true);
//    }

    public static PostMethod buildRequestPost(HttpServletRequest request, HttpClientData httpClientData) throws RestServiceException {
        PostMethod method = new PostMethod();
        try {
            StringRequestEntity requestEntity = new StringRequestEntity(
                    httpClientData.getPayload(),
//                    httpClientData.buildPayload(),
                    httpClientData.getContentType(),
                    httpClientData.getEncoding());

            String url = httpClientData.getUrl(); //URL del granting ticket

            logger.info(String.format("POST Request URL: [%s]", url));

//            method = new PostMethod(url);
            method.setURI(new URI(url, false));
            method.setRequestEntity(requestEntity);

            return method;
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            logger.error(String.format("A error has occurred at the moment of invoke the services [%s].", method.getPath()));
            throw new RestServiceException(e.getMessage());
        } catch (URIException e) {
//            e.printStackTrace();
            logger.error(String.format("A error has occurred at the moment of invoke the services [%s].", method.getPath()));
            throw new RestServiceException(e.getMessage());
        }
    }

    public static void doRequest(HttpMethodBase method) throws RestServiceException {
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(method);
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error(String.format("A error has occurred at the moment of invoke the services [%s].", method.getPath()));
            throw new RestServiceException(e.getMessage(), e.getCause());
        }
    }

    public static String validateResponse(HttpMethodBase method, String encoding) throws RestServiceException {
        String output;

        try {

            int httpCode = method.getStatusCode();
            logger.info(String.format("HTTP Response Code: [%d]", httpCode));
            printHeader(method);

            InputStream stream = method.getResponseBodyAsStream();
//            output = IOUtils.toString(stream, "UTF-8");
//            output = IOUtils.toString(stream, grantingTicket.getEncoding());
            output = IOUtils.toString(stream, encoding);

            String errorMsg;

            if (httpCode == 200) {
                if (output != null) {
                    logger.info(String.format("Response Body: [%s]", output));
                } else {
                    throw new RestServiceException("Response Body is null");
                }
            }/* else {
                if (output != null) {
                    if (!output.equals("")) {
                        ASOResponse ASOResponse;
                        ASOResponse = (ASOResponse) GsonUtility.jsonElementObjectToObject(output, null, ASOResponse.class, null);
                        logger.warn(ASOResponse.toString());

                        errorMsg = String.format("Error has occurred with the services [%s] : ", ASOResponse.toString());
                        logger.error(String.format("Error in the services: [%s]", method.getPath()));
                        logger.error(errorMsg);
                        throw new RestServiceException(errorMsg);
                    } else {
                        errorMsg = String.format("The response of the services [%s] is: [%s]", method.getPath(), method.getStatusLine().toString());
                        logger.warn(errorMsg);
                        throw new RestServiceException(errorMsg);
                    }
                } else {
                    String responseWarningCode = method.getResponseHeader("ResponseWarningCode").getValue();
                    String responseWarningMsg = method.getResponseHeader("ResponseWarningDescription").getValue();
                    errorMsg = String.format("Service warning [%s]: %s", responseWarningCode, responseWarningMsg);
                    logger.warn(errorMsg);
                    throw new RestServiceException(errorMsg);
                }
            }*/
        } catch (IOException e) {
            logger.error(String.format("A error has occurred at the moment of invoke the services [%s].", method.getPath()));

            throw new RestServiceException(e.getMessage());
        }

        return output;
    }

//    private static void setDefaultAsoHeaders(HttpServletRequest request, HttpMethodBase method, HttpClientData httpClientData) {
//        setHeaderToMethod(method, "ContactId", (String) HttpUtility.getSessionAttribute(request, IV_USER_ATTR));
//        setHeaderToMethod(method, "tsec", (String) HttpUtility.getSessionAttribute(request, TSEC_ATTR));
//        setHeaderToMethod(method, "ConsumerRequestID", httpClientData.getConsumerRequestId());
//        setHeaderToMethod(method, "Content-Type", httpClientData.getContentType());
//    }

	public static void setHeaderToMethod(HttpMethodBase method, String headerKey, String headerValue) {
		Header header =  new Header();
		header.setName(headerKey);
		header.setValue(headerValue);

		method.setRequestHeader(header);
	}

	private static void printHeader(HttpMethodBase method) {
		logger.debug("+++++ BEGIN RESPONSE HEADER +++++");
		for (Header header : method.getResponseHeaders()) {
			logger.debug(String.format("key: %s value: %s", header.getName(), header.getValue()));
		}
		logger.debug("+++++  END RESPONSE HEADER  +++++");
	}

}
