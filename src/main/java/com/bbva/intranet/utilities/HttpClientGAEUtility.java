package com.bbva.intranet.utilities;

import com.bbva.intranet.utilities.exceptions.RestServiceException;
import com.bbva.intranet.vo.HttpClientData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

@Component
@Deprecated
public abstract class HttpClientGAEUtility implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientGAEUtility.class);

    private static int httpCode;
    private static String contentType;
    private static int responseCode;
    private static String responseMessage;

    public static HttpURLConnection buildRequestGet(HttpClientData httpClientData) throws RestServiceException {
        HttpURLConnection conn = null;
        String urlStr = httpClientData.getUrl();
        try {
            URL url = new URL(urlStr);
            logger.info(String.format("GET Request URL: [%s]", urlStr));

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

        } catch (MalformedURLException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        }

        return conn;
    }

    public static HttpURLConnection buildRequestPost(HttpClientData httpClientData) throws RestServiceException {
        HttpURLConnection conn = null;
        String urlStr = httpClientData.getUrl();

        try {
            URL url = new URL(urlStr);
            logger.info(String.format("POST Request URL: [%s]", url));

            conn = (HttpURLConnection) url.openConnection();
            if (httpClientData.getContentType() != null && !httpClientData.getContentType().isEmpty()) {
                conn.setRequestProperty("Content-Type", httpClientData.getContentType());
            }
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

        } catch (UnsupportedEncodingException e) {
            throwsErrorException(e, urlStr);
        } catch (ProtocolException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        } catch (MalformedURLException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        }

        return conn;
    }

    public static HttpURLConnection buildRequestPut(HttpClientData httpClientData) throws RestServiceException {
        HttpURLConnection conn = null;
        String urlStr = httpClientData.getUrl();

        try {
            URL url = new URL(urlStr);
            logger.info(String.format("PUT Request URL: [%s]", url));

            conn = (HttpURLConnection) url.openConnection();
            if (httpClientData.getContentType() != null && !httpClientData.getContentType().isEmpty()) {
                conn.setRequestProperty("Content-Type", httpClientData.getContentType());
            }
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);

        } catch (UnsupportedEncodingException e) {
            throwsErrorException(e, urlStr);
        } catch (ProtocolException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        } catch (MalformedURLException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        }

        return conn;
    }

    public static HttpURLConnection buildRequestDelete(HttpClientData httpClientData) throws RestServiceException {
        HttpURLConnection conn = null;
        String urlStr = httpClientData.getUrl();
        try {
            URL url = new URL(urlStr);
            logger.info(String.format("DELETE Request URL: [%s]", urlStr));

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);

        } catch (MalformedURLException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            closeHttpURLConnection(conn);
            throwsErrorException(e, urlStr);
        }

        return conn;
    }

    public static void sendPayload(HttpURLConnection conn, HttpClientData httpClientData) throws RestServiceException {
//    public static void sendPayload(HttpURLConnection conn, String payload) throws RestServiceException {
        try {
            //
//            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
//            osw.write(URLEncoder.encode(httpClientData.getPayload(), httpClientData.getEncoding()));
//            osw.close();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, httpClientData.getEncoding()));
            writer.write(httpClientData.getPayload());
            writer.close();
            wr.close();

            // Send the Payload
//            OutputStream os = conn.getOutputStream();
//            os.write(httpClientData.getPayload().getBytes());
//            os.close();
        } catch (IOException e) {
            throwsErrorException(e, httpClientData.getUrl());
        }
    }

    public static String validateResponse(HttpURLConnection conn, String encoding) throws RestServiceException {
        String output = null;
        BufferedReader reader = null;
        String urlStr = conn.getURL().toString();
        try {
            StringBuffer response = new StringBuffer();
            String line;

//            int httpCode = conn.getResponseCode();
            httpCode = conn.getResponseCode();
            contentType = conn.getContentType();
            responseCode = conn.getResponseCode();
            responseMessage = conn.getResponseMessage();
            logger.info(String.format("HTTP Response Code: [%d]", httpCode));
            printHeader(conn);

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
//                reader.close();

            output = response.toString();
            logger.info(String.format("Response Body: [%s]", output));

//            if (httpCode != HttpURLConnection.HTTP_OK) {
//                // When the Code Response its different to 200 OK build a AppCNResponse with the detail.
////                String errorMsg = String.format("Response Error: [%s] : %s", conn.getResponseCode(), conn.getResponseMessage());
//                String errorMsg = String.format("Response Error: %s [%s] : %s", conn.getResponseMessage(), conn.getResponseCode(), output);
//                logger.info(errorMsg);
////                HttpClientResponse httpClientResponse = new HttpClientResponse();
//                HttpClientResponse httpClientResponse = (HttpClientResponse) GsonUtility.jsonToObject(output, null, HttpClientResponse.class);
//                httpClientResponse.setCode(String.format("%d", httpCode));
//                httpClientResponse.setMessage(String.format("%s", conn.getResponseMessage()));
//
//                output = GsonUtility.objectToJson(httpClientResponse);
//            }
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeHttpURLConnection(conn);
        }

        return output;
    }

    public static int getHttpCode() {
        return httpCode;
    }

    public static String getContentType() {
        return contentType;
    }

    public static String getResponseMessage() {
        return responseMessage;
    }

    public static int getResponseCode() {
        return responseCode;
    }

    public static void fillHeaders(HttpURLConnection conn, Map<String, String> headers) {
        for (String header : headers.keySet()) {
            setHeaderToMethod(conn, header, headers.get(header));
        }
    }

	private static void setHeaderToMethod(HttpURLConnection conn, String headerKey, String headerValue) {
		conn.setRequestProperty(headerKey, headerValue);
	}

	private static void printHeader(HttpURLConnection conn) {
		Map<String, List<String>> headers = conn.getHeaderFields();
        logger.debug("+++++ BEGIN RESPONSE HEADER +++++");
		for (String header : headers.keySet()) {
            for (String value : headers.get(header)) {
                logger.debug(String.format("key: %s value: %s", header, value));
            }
		}
		logger.debug("+++++  END RESPONSE HEADER  +++++");
	}
    
    private static void throwsErrorException(Exception e, String urlStr) throws RestServiceException {
        logger.error(String.format("A error has occurred at the moment of invoke the services [%s].", urlStr));
        throw new RestServiceException(e.getMessage());
    }

    private static void closeHttpURLConnection(HttpURLConnection conn) {
        if (conn != null) {
            conn.disconnect();
        }
    }

}
