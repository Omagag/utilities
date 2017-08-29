package com.bbva.intranet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by Admin on 26/01/2016.
 */
public abstract class HttpUtility {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtility.class);

    public static void setRequestAttribute(HttpServletRequest request, String attrName, Object value) {
        request.setAttribute(attrName, value);
    }

    public static Object getRequestAttribute(HttpServletRequest request, String attrName) {
        return request.getAttribute(attrName);
    }

    public static Object getRequestParameter(HttpServletRequest request, String paramName) {
        return request.getParameter(paramName);
    }

    public static void setSessionAttribute(HttpServletRequest request, String attrName, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(attrName, value);
    }

    public static Object getSessionAttribute(HttpServletRequest request, String attrName) {
        HttpSession session = request.getSession();
        return session.getAttribute(attrName);
    }

    public static void removeSessionAttribute(HttpServletRequest request, String attrName) {
        HttpSession session = request.getSession();
        session.removeAttribute(attrName);
    }

    public static void printRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            logger.info(String.format("Header: %s=[%s]", headerName, headerValue));
        }
    }

    public static String getHeaderValue(HttpServletRequest request, String headerName) {
        return request.getHeader(headerName);
    }

}
