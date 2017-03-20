package com.thinkcloudgroup.shopapp.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

	@RequestMapping(method=RequestMethod.POST, 
		    consumes = "application/json")
	//@ResponseBody
	public User create(@RequestBody User user) {
		return service.create(user);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes = "application/json", value="{id}")
	public User update(@PathVariable String id, @RequestBody User user) {
		return service.update(id, user);
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
