package com.thinkcloudgroup.shopapp.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/*import org.baeldung.persistence.model.User;
import org.baeldung.persistence.model.VerificationToken;
import org.baeldung.registration.OnRegistrationCompleteEvent;
import org.baeldung.security.ISecurityUserService;
import org.baeldung.service.IUserService;
import org.baeldung.web.dto.PasswordDto;
import org.baeldung.web.dto.UserDto;
import org.baeldung.web.error.InvalidOldPasswordException;
import org.baeldung.web.util.GenericResponse;
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkcloudgroup.shopapp.objects.User;
import com.thinkcloudgroup.shopapp.registration.OnRegistrationCompleteEvent;
import com.thinkcloudgroup.shopapp.service.IUserService;

//import com.thinkcloudgroup.shopapp.util.GenericResponse;
import org.springframework.context.ApplicationEventPublisher;
//import src.main.java.org.baeldung.web.controller.OnRegistrationCompleteEvent;

//import src.main.java.org.baeldung.web.controller.UserDto;

@RestController
//@RequestMapping("/registration")
public class RegistrationCtrl  extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    //@Autowired
    //private ISecurityUserService securityUserService;

    //@Autowired
    //private MessageSource messages;

//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    //@Autowired
    //private Environment env;

    public RegistrationCtrl() {
        super();
    }

    // Registration

    //@RequestMapping(value = "/users/registration", method = RequestMethod.POST, consumes = "application/json")//, consumes = "application/json"
    //@RequestMapping(method = RequestMethod.POST, consumes = "application/json")//, consumes = "application/json"
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    //public String registerUserAccount(@Valid @RequestBody User user, Errors errors) throws JsonProcessingException {
    public String registerUserAccount(@RequestParam("firstName") String fn, @RequestParam("lastName") String ln, 
    		@RequestParam("username") String un, @RequestParam("password") String pw, @RequestParam("address") String ad,
    		@RequestParam("city") String ci, @RequestParam("country") String co, @RequestParam("roles") String ros,
    		@RequestParam("role") String ro) {
    //public String registerUserAccount(@RequestBody User fn) {
    	
    	// LOGGER.info("Registering user account with information: {}", user);
/*
         final User registered = userService.registerNewUserAccount(accountDto);
         eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
         return new GenericResponse("success");
         */
    	
    	/*
    	ObjectMapper mapper = new ObjectMapper();
    	//Object to JSON in String
    	String jsonInString;
		try {
			jsonInString = mapper.writeValueAsString(fn);  	
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{\"error\":1, \"error\": \"Exception with user to json cast\" }";  	
		}
		*/
    	//LOGGER.info("Spring in Action");
    	//return "Spring in Action: "+jsonInString;
    	//return "{\"success\":0, \"error\": \"SPRING IN ACTION\", \"user\":"+fn+":"+ln+"}";
    	//return "Spring in Action: "+jsonInString;
    	
    	
    	
        //LOGGER.debug("Registering user account with information: {}", user);
     // Validate username
        User userExist = userService.findByUsername(un);
        /*
        ObjectMapper mapper = new ObjectMapper();
    	//Object to JSON in String
    	String jsonInString;
		try {
			jsonInString = mapper.writeValueAsString(user);  	
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{\"error\":1, \"error\": \"Exception with user to json cast\" }";  	
		}*/
        
        if (userExist != null) {
        	return "{\"success\":0, \"error\": \"Username already exist\", please use another \"username\":"+un+"}";  	
        } else { 
        	if(un.isEmpty() || un.isEmpty()){
        		return "{\"success\":0, \"error\": \"Username or Password can´t be empty\", \"username\":"+un+"}"; 
        	}else{
        		User user = new User(un,pw);//new User(fn, ln, un, pw, ad, ci, co, ros, ro);
        		user.setFirstName(fn);
        		user.setLastName(ln);
        		user.setAddress(ad);
        		user.setCity(ci);
        		user.setCountry(co);
        		user.setRoles(ros);
        		user.setRole(ro);
        		User createdUser = userService.create(user);
            	int answer = (createdUser.getId().isEmpty())?0:1;
                return "{\"success\":"+answer+", \"user\":"+un+"}";
        	}
        }
        
/*
        final User registered = userService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
*/
    }
/*
    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);
            System.out.println(user);
            if (user.isUsing2FA()) {
                model.addAttribute("qr", userService.generateQRUrl(user));
                return "redirect:/qrcode.html?lang=" + locale.getLanguage();
            }
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }

        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/badUser.html?lang=" + locale.getLanguage();
    }

    // user activation - verification

    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(newToken.getToken());
        mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user));
        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }

    // Reset password
    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID()
                .toString();
            userService.createPasswordResetTokenForUser(user, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
        }
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
        final String result = securityUserService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = (User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
    }

    // change user password
    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
    }

    @RequestMapping(value = "/user/update/2fa", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse modifyUser2FA(@RequestParam("use2FA") final boolean use2FA) throws UnsupportedEncodingException {
        final User user = userService.updateUser2FA(use2FA);
        if (use2FA) {
            return new GenericResponse(userService.generateQRUrl(user));
        }
        return null;
    }

    // ============== NON-API ============

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

   
*/
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
