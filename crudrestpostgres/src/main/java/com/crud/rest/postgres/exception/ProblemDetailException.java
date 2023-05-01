package com.crud.rest.postgres.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;


@Data
public class ProblemDetailException  extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
	private final HttpStatus ststuscode;
	private final String errormessage;
	
	public ProblemDetailException(HttpStatus ststuscode, String errormessage) {
		 this.ststuscode = ststuscode;
		 this.errormessage = errormessage;
	}
}
