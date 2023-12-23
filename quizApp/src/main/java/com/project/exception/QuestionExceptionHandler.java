package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuestionExceptionHandler
{
	
	  @ExceptionHandler( value = InternalServerErrorException.class) 
	  public ResponseEntity<Object> internalServerError(InternalServerErrorException e)
	  {
		  return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  	
	@ExceptionHandler( value = QuestionNotFoundException.class)
	public ResponseEntity<Object> questionNotFoundException(QuestionNotFoundException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler( value = QuizNotFoundException.class)
	public ResponseEntity<Object> quizNotFoundException(QuizNotFoundException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
}
