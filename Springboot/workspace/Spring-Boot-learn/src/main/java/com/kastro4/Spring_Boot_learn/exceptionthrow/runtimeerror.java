package com.kastro4.Spring_Boot_learn.exceptionthrow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;




@ResponseStatus(value =HttpStatus.NOT_FOUND)
public class runtimeerror  extends RuntimeException{
	
	
	

	public runtimeerror(String message) {
		super(message);
	}
}
