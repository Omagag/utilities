package com.bbva.intranet.utilities;

import com.bbva.intranet.utilities.exceptions.HttpClientException;
import com.bbva.intranet.utilities.vos.HttpClientData;
import com.bbva.intranet.utilities.vos.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

public abstract class HttpClientSimpleUtility implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientSimpleUtility.class);

    public static HttpResponse buildRequestGet(HttpClientData httpClientData) throws HttpClientException {
        HttpResponse httpResponse = null;
        HttpURLConnection conn = null;
        String urlStr = httpClientData.getUrl();
        try {
            URL url = new URL(urlStr);
            logger.info(String.format("GET Request URL: [%s]", urlStr));

            conn = (HttpURLConnection) url.openConnection();
            if (httpClientData.getContentType() != null && !httpClientData.getContentType().isEmpty()) {
                conn.setRequestProperty("Content-Type", httpClientData.getContentType());
            }
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            fillHeaders(conn, httpClientData.getHeaders());

            httpResponse = validateResponse(conn);
        } catch (MalformedURLException e) {
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } finally {
            closeHttpURLConnection(conn);
        }

//        return conn;
        return httpResponse;
    }

//    public static HttpURLConnection buildRequestPost(HttpClientData httpClientData) throws HttpClientException {
    public static HttpResponse buildRequestPost(HttpClientData httpClientData) throws HttpClientException {
        HttpResponse httpResponse = null;
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

            fillHeaders(conn, httpClientData.getHeaders());

            sendPayload(conn, httpClientData);

            httpResponse = validateResponse(conn);
        } catch (UnsupportedEncodingException e) {
            throwsErrorException(e, urlStr);
        } catch (ProtocolException e) {
            throwsErrorException(e, urlStr);
        } catch (MalformedURLException e) {
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } finally {
            closeHttpURLConnection(conn);
        }

//        return conn;
        return httpResponse;
    }

//    public static HttpsURLConnection buildSslRequestPost(HttpClientData httpClientData) throws HttpClientException {
    public static HttpResponse buildSslRequestPost(HttpClientData httpClientData) throws HttpClientException {
        HttpResponse httpResponse = null;
        HttpsURLConnection conn = null;
        String urlStr = httpClientData.getUrl();

        try {
            final URL url;
            if (!httpClientData.isValidateCerts()) {
                url = trustAndInstallAllCerts(urlStr);
            } else {
                url = new URL(urlStr);
            }
            logger.info(String.format("POST SSL Request URL: [%s]", url));

            conn = (HttpsURLConnection) url.openConnection();

            // TODO: Add the capability of Trust in ANY HOST (warning!!!)
            trustAnyHost(conn, url);

            if (httpClientData.getContentType() != null && !httpClientData.getContentType().isEmpty()) {
                conn.setRequestProperty("Content-Type", httpClientData.getContentType());
            }
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            fillHeaders(conn, httpClientData.getHeaders());

            sendPayload(conn, httpClientData);

            httpResponse = validateResponse(conn);
        } catch (UnsupportedEncodingException e) {
            throwsErrorException(e, urlStr);
        } catch (ProtocolException e) {
            throwsErrorException(e, urlStr);
        } catch (MalformedURLException e) {
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } catch (NoSuchAlgorithmException e) {
            throwsErrorException(e, urlStr);
        } catch (KeyManagementException e) {
            throwsErrorException(e, urlStr);
        } finally {
            closeHttpURLConnection(conn);
        }

//        return conn;
        return httpResponse;
    }

    public static HttpResponse buildRequestPut(HttpClientData httpClientData) throws HttpClientException {
        HttpResponse httpResponse = null;
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

            fillHeaders(conn, httpClientData.getHeaders());

            sendPayload(conn, httpClientData);

            httpResponse = validateResponse(conn);
        } catch (UnsupportedEncodingException e) {
            throwsErrorException(e, urlStr);
        } catch (ProtocolException e) {
            throwsErrorException(e, urlStr);
        } catch (MalformedURLException e) {
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } finally {
            closeHttpURLConnection(conn);
        }

        return httpResponse;
    }

    public static HttpResponse buildSslRequestPut(HttpClientData httpClientData) throws HttpClientException {
        HttpResponse httpResponse = null;
        HttpsURLConnection conn = null;
        String urlStr = httpClientData.getUrl();

        try {
            final URL url;
            if (!httpClientData.isValidateCerts()) {
                url = trustAndInstallAllCerts(urlStr);
            } else {
                url = new URL(urlStr);
            }
            logger.info(String.format("PUT SSL Request URL: [%s]", url));

            conn = (HttpsURLConnection) url.openConnection();

            // TODO: Add the capability of Trust in ANY HOST (warning!!!)
            trustAnyHost(conn, url);

            if (httpClientData.getContentType() != null && !httpClientData.getContentType().isEmpty()) {
                conn.setRequestProperty("Content-Type", httpClientData.getContentType());
            }
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);

            fillHeaders(conn, httpClientData.getHeaders());

            sendPayload(conn, httpClientData);

            httpResponse = validateResponse(conn);
        } catch (UnsupportedEncodingException e) {
            throwsErrorException(e, urlStr);
        } catch (ProtocolException e) {
            throwsErrorException(e, urlStr);
        } catch (MalformedURLException e) {
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } catch (NoSuchAlgorithmException e) {
            throwsErrorException(e, urlStr);
        } catch (KeyManagementException e) {
            throwsErrorException(e, urlStr);
        } finally {
            closeHttpURLConnection(conn);
        }

//        return conn;
        return httpResponse;
    }

    public static HttpResponse buildRequestDelete(HttpClientData httpClientData) throws HttpClientException {
        HttpResponse httpResponse = null;
        HttpURLConnection conn = null;
        String urlStr = httpClientData.getUrl();
        try {
            URL url = new URL(urlStr);
            logger.info(String.format("DELETE Request URL: [%s]", urlStr));

            conn = (HttpURLConnection) url.openConnection();
            if (httpClientData.getContentType() != null && !httpClientData.getContentType().isEmpty()) {
                conn.setRequestProperty("Content-Type", httpClientData.getContentType());
            }
            conn.setConnectTimeout(httpClientData.getTimeout());
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);

            fillHeaders(conn, httpClientData.getHeaders());

            httpResponse = validateResponse(conn);
        } catch (MalformedURLException e) {
            throwsErrorException(e, urlStr);
        } catch (IOException e) {
            throwsErrorException(e, urlStr);
        } finally {
            closeHttpURLConnection(conn);
        }

        return httpResponse;
    }

    private static void sendPayload(HttpURLConnection conn, HttpClientData httpClientData) throws HttpClientException {
//    public static void sendPayload(HttpURLConnection conn, String payload) throws RestServiceException {
        try {
            //
//            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
//            osw.write(URLEncoder.encode(httpClientData.getPayload(), httpClientData.getEncoding()));
//            osw.close();

            if (httpClientData.getEncoding() == null) {
                throw new HttpClientException("Encoding is necessary.");
            }

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

    private static HttpResponse validateResponse(HttpURLConnection conn) throws HttpClientException {
        HttpResponse httpResponse = new HttpResponse();
        BufferedReader reader = null;
        String urlStr = conn.getURL().toString();
        try {
            httpResponse.setCode(conn.getResponseCode());
            httpResponse.setContentType(conn.getContentType());
            httpResponse.setHeaders(conn.getHeaderFields());
            httpResponse.setMessage(conn.getResponseMessage());

            httpResponse.printHeader();

            StringBuilder response = new StringBuilder();
            String line;
            try {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } catch (IOException ioe) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            httpResponse.setOutput(response.toString());
            logger.info(String.format("HTTP Response [%s]", httpResponse.toString()));
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

        return httpResponse;
    }

    private static URL trustAndInstallAllCerts(String strUrl) throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException {
        URL url;
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Now you can access an https URL without having the certificate in the truststore
        url = new URL(strUrl);
        return url;
    }

    private static void trustAnyHost(HttpsURLConnection conn, final URL url) {
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                return hostname.equals(url.getHost());
            }
        });
    }

    private static void fillHeaders(HttpURLConnection conn, Map<String, String> headers) {
        if (headers != null) {
            for (String header : headers.keySet()) {
                setHeaderToMethod(conn, header, headers.get(header));
            }
        }
    }

	private static void setHeaderToMethod(HttpURLConnection conn, String headerKey, String headerValue) {
		conn.setRequestProperty(headerKey, headerValue);
	}

	private static void throwsErrorException(Exception e, String urlStr) throws HttpClientException {
        logger.error(String.format("A error has occurred at the moment of invoke the services <%s>.", urlStr));
        e.printStackTrace();
        throw new HttpClientException(e.getMessage());
    }

    private static void closeHttpURLConnection(HttpURLConnection conn) {
        if (conn != null) {
            conn.disconnect();
        }
    }

}
