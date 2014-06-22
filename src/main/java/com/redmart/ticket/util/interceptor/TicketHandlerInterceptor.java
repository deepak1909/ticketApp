package com.redmart.ticket.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.redmart.ticket.vo.UserProfile;

/**
 * The Class TicketHandlerInterceptor.
 */
public class TicketHandlerInterceptor extends HandlerInterceptorAdapter {


    /* (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	//Allows only logged-in users
    	HttpSession session = request.getSession();
    	UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");
    	if (userProfile == null) {
            response.sendRedirect("/redmart/user/login");
            return false;
        } else {
            return true;
        }
    }

}
