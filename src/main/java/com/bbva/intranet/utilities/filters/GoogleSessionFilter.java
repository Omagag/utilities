package com.bbva.intranet.utilities.filters;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by BBVA on 27/04/2016.
 */
public class GoogleSessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(GoogleSessionFilter.class);

    public static final String CURRENT_USER_GAE = "currentUserGAE";

    private boolean enableAuthentication;
    private List<String> freeAccessActions;

    /**
     * Default constructor.
     */
    public GoogleSessionFilter() {}

    /**
     * @see Filter#destroy()
     */
    public void destroy() {	}

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession();

        if (enableAuthentication) {
            // Request uri, ctcPath and actual action
            String uri = request.getRequestURI();
            String ctxPath = request.getContextPath();
            String action = uri.substring(ctxPath.length());

            if (!isFreeAction(action)) {

                String basePath = String.format("%s/", ctxPath);
                session.setAttribute("basePath", basePath);

                UserService userService = UserServiceFactory.getUserService();
                boolean authenticated = isAuthenticated(request);

                if (authenticated) {
                    // Store into Session the User logged on
                    User currentUserGAE = userService.getCurrentUser();
                    session.setAttribute(CURRENT_USER_GAE, currentUserGAE);

                    chain.doFilter(request, response);
                    return;
                }

                response.sendRedirect(String.format("%s%s", ctxPath, userService.createLoginURL(uri))); // Redirect to Login
                return;
            }
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    public void setFreeAccessActions(List<String> freeAccessActions) {
        this.freeAccessActions = freeAccessActions;
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        return (request.getUserPrincipal() != null);
    }

    public void setEnableAuthentication(boolean enableAuthentication) {
        this.enableAuthentication = enableAuthentication;
    }

    private boolean isFreeAction(String action) {
        boolean free = false;
        if (freeAccessActions != null) {
            for (String freeAction : freeAccessActions) {
                if (action.startsWith(freeAction)) {
                    free = true;
                }
            }
        }

        return free;
    }
}
