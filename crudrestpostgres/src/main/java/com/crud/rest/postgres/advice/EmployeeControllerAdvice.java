package com.crud.rest.postgres.advice;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.crud.rest.postgres.exception.ProblemDetailException;

@RestControllerAdvice
public class EmployeeControllerAdvice extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2026951275278372360L;

	@ExceptionHandler(value = { ProblemDetailException.class })
	public ProblemDetail resourceNotFoundException(ProblemDetailException ex, WebRequest request) {
		 ProblemDetail body = ProblemDetail
			        .forStatusAndDetail(HttpStatusCode.valueOf(ex.getStstuscode().value()),ex.getErrormessage());
			    body.setType(URI.create("https://bankname.com/common-problems/low-balance"));
			    body.setTitle("Record Not Found");
		return body;
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ProblemDetail exception(Exception ex, WebRequest request) {
		 ProblemDetail body = ProblemDetail
			        .forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),ex.getMessage());
			    body.setType(URI.create("https://bankname.com/common-problems/low-balance"));
			    body.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return body;
	}

}
