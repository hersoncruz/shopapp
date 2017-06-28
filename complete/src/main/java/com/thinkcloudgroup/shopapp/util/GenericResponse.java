package com.thinkcloudgroup.shopapp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class GenericResponse {
	private Integer success;
    private String message;
    private String error;

    public GenericResponse(final String message) {
        super();
        this.setSuccess(1);
        this.message = message;
    }

    public GenericResponse(final String message, final String error) {
        super();
        if(message!=null){
        	this.setSuccess(1);
        }
        
        this.message = message;
        this.error = error;
    }

    public GenericResponse(final List<FieldError> fieldErrors, final List<ObjectError> globalErrors) {
        super();
        this.setSuccess(0);
        final ObjectMapper mapper = new ObjectMapper();
        try {
            this.message = mapper.writeValueAsString(fieldErrors);
            this.error = mapper.writeValueAsString(globalErrors);
        } catch (final JsonProcessingException e) {
            this.message = "";
            this.error = "";
        }
    }

    public String getMessage() {
        return new Gson().toJson(this.message);
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getError() {
    	return new Gson().toJson(this.error);
        //return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

}
