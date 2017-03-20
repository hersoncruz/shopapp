package com.thinkcloudgroup.shopapp.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thinkcloudgroup.shopapp.objects.User;
import com.thinkcloudgroup.shopapp.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserCtrl {
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
	public User create(@RequestBody String str) {
		User ob = new User();
		//return ob;
		ob.setId(str);
		return service.create(ob);
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
	public void doPost(@RequestBody User user) {
		 String firstName = user.firstName;
		 String lastName = user.lastName;
		 String username = user.username;
		 String password = user.password;
		 String address = user.address;
		 String city = user.city;
		 String country = user.country;
		 Boolean activated = user.activated;
		 String activationCode = user.activationCode;
		 String role = user.role;
	    // User obj1 = user.getObj1;
	     //User obj2 = wrapperObj.getObj2;

	     //Do what you want with the objects...


	}*/
	
	@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(Exception ex) {
    }
}
