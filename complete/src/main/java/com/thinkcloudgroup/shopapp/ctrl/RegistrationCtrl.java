package com.thinkcloudgroup.shopapp.ctrl;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.thinkcloudgroup.shopapp.model.VerificationToken;
import com.thinkcloudgroup.shopapp.objects.User;
import com.thinkcloudgroup.shopapp.service.IUserService;
import com.thinkcloudgroup.shopapp.util.GenericResponse;

@RestController
public class RegistrationCtrl  extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private Environment env;

    public RegistrationCtrl() {
        super();
    }

    // Registration
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public GenericResponse registerUserAccount(final HttpServletRequest request, @RequestParam("firstName") String fn, @RequestParam("lastName") String ln, 
    		@RequestParam("username") String un, @RequestParam("password") String pw, @RequestParam("address") String ad,
    		@RequestParam("city") String ci, @RequestParam("country") String co) {
   
    	// Validate username
        User userExist = userService.findByUsername(un);
       
        String response = "";
        if (userExist != null) {
        	response = messages.getMessage("message.regError", null, request.getLocale());
            return new GenericResponse(null, response);
        } else { 
        	if(un.isEmpty() || un.isEmpty()){
        		response = messages.getMessage("NotEmpty.user.username", null, request.getLocale());
                return new GenericResponse(null, response);
        	}else{
        		User user = new User(un,pw);
        		user.setFirstName(fn);
        		user.setLastName(ln);
        		user.setAddress(ad);
        		user.setCity(ci);
        		user.setCountry(co);
        		user.setRoles("USER");
        		User createdUser = userService.create(user);
            	//String answer = (createdUser.getId().isEmpty())?"0":"1";
            	response = messages.getMessage("message.regSucc", null, request.getLocale());
                return new GenericResponse(response, null);
        	}
        }
    }

    // Forgot password
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse forgotPassword(final HttpServletRequest request, @RequestParam("username") final String userEmail) {
        final User user = userService.findByUsername(userEmail);
        String response = "";
        if (user != null) {
            final String token = UUID.randomUUID()
                .toString();
            userService.createPasswordResetTokenForUser(user, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
            response = messages.getMessage("message.resetPasswordEmail", null, request.getLocale());
            return new GenericResponse(response);
        }else{
        	response = messages.getMessage("ValidEmail.user.email", null, request.getLocale());
        	return new GenericResponse(null,response);
        }
    }
    
    // Reset password
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, final Locale locale, final Model model, @RequestParam("id") final String id, @RequestParam("token") final String token, @RequestParam("newpassword") final String newpassword) {
        final Boolean result = userService.validatePasswordResetToken(id, token, newpassword);
        String response = "";
        if (result) {
            response = messages.getMessage("message.updatePasswordSuc", null, request.getLocale());
            return new GenericResponse(response,null);
        }else{
        	response = messages.getMessage("invalid.token", null, request.getLocale());
        	return new GenericResponse(null,response);
        }
    }  
	
    // ============== NON-API ============

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = /*contextPath +*/ "http://localhost:8084/#!/resetpassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getUsername());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
