package com.abhinav.socialmedia.exceptions;

import com.abhinav.socialmedia.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorDto handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("handleResourceNotFoundException ",ex);
		ErrorDto errorDTO = new ErrorDto(ex.getMessage());
		return errorDTO;
	}
}
