package com.thinkcloudgroup.shopapp.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkcloudgroup.shopapp.objects.User;
import com.thinkcloudgroup.shopapp.service.IUserService;

@ControllerAdvice
@RestController
@RequestMapping("/user")
public class UserCtrl extends ResponseEntityExceptionHandler {
	private IUserService service;

	@Autowired
	public UserCtrl(IUserService service){
		this.service = service;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getAll() {
		return service.getAllObjects();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public User getById(@PathVariable String id) {
		return service.findById(id);
	}

	@RequestMapping(method=RequestMethod.POST, consumes = "application/json")
	public String create(@Valid @RequestBody User user, Errors errors) throws JsonProcessingException {    
		// Validate username
        List<User> userExist = service.findByUsername(user.getUsername());
        
        if (userExist.size() > 0) {
        	ObjectMapper mapper = new ObjectMapper();
        	//Object to JSON in String
        	String jsonInString = mapper.writeValueAsString(user);
        	
        	return "{\"success\":0, \"error\": \"Username already exist\", \"user\":"+jsonInString+"}";  	
        } else {
        	User createdUser = service.create(user);
        	int answer = (createdUser.getId().isEmpty())?0:1;
        	ObjectMapper mapper = new ObjectMapper();
        	//Object to JSON in String
        	String jsonInString = mapper.writeValueAsString(createdUser);

        	return "{\"success\":"+answer+", \"user\":"+jsonInString+"}";
        }
	}

	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes = "application/json", value="{id}")
	public String update(@PathVariable String id, @RequestBody User user) throws JsonProcessingException {
		
		// Validate username, this will avoid a user to update his username by checking its now username does not exist for other user
        List <User> userExist = service.findByUsername(user.getUsername());
        boolean saveIt = false;
        if (userExist.size() > 0 ) {
        	if(userExist.size() == 1){
        		for (User temp : userExist) {
            		if(temp.getId().compareTo(id) == 0 ){
            			saveIt = true;
            		}
            	}
        	}  		
        }else if(userExist.size() == 0){
    		saveIt = true;
    	} 
        
        if(saveIt){
        	ObjectMapper mapper = new ObjectMapper();
        	//Object to JSON in String
        	String jsonInString = mapper.writeValueAsString(service.update(id, user));
        	return "{\"success\":1, \"user\":"+jsonInString+"}";
        }else{
        	
        	ObjectMapper mapper = new ObjectMapper();
        	//Object to JSON in String
        	String jsonInString = mapper.writeValueAsString(user);
        	return "{\"success\":0, \"error\": \"Username already exist\", \"user\":"+jsonInString+"}";  
        }
	}	

	/*
	@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(Exception ex) {
    }
	*/
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
