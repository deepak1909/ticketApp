package com.redmart.ticket.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redmart.ticket.domain.User;
import com.redmart.ticket.service.LoginService;
import com.redmart.ticket.util.exception.ApplicationException;
import com.redmart.ticket.vo.UserProfile;

/**
 * The Class LoginController.
 */
@Controller
public class LoginController {

	/** The login service. */
	@Autowired
	private LoginService loginService;

	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Login page.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "user/login", method = RequestMethod.GET)
	public String loginPage() {
		
		LOGGER.info("ENTERING LOGIN PAGE");
		return "login/login";

	}

	/**
	 * Authenticate.
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String authenticate(HttpServletRequest request, ModelMap model) {
		
		try {
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			User user = loginService.authenticate(userId, password);
			
			if(user != null){
				UserProfile userProfile = new UserProfile();
				userProfile.setId(user.getId());
				userProfile.setEmail(user.getEmail());
				userProfile.setFirstName(user.getFirstName());
				userProfile.setLastName(user.getLastName());
				
				HttpSession session = request.getSession(false);
				System.out.println(session+ " "+userProfile);
				session.setAttribute("userProfile", userProfile);
				LOGGER.info("USER AUTHENTICATION SUCCESS");
				return "redirect:/ticket/dashboard";
			}
			
			LOGGER.info("USER AUTHENTICATION FAILURE");
			model.addAttribute("error", "Invalid credentials");
			return "login/login";
		} catch (ApplicationException e) {
			model.addAttribute("error", e.getMsg());
			return "error/error";
		}

	}

}
