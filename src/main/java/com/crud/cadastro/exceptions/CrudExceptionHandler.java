package com.crud.cadastro.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CrudExceptionHandler extends ResponseEntityExceptionHandler{

	public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception ex, WebRequest resquest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
