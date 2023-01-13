package com.demo.web.exception;

import com.demo.web.payload.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@ControllerAdvice
@Slf4j
public class ExceptionHelper {
	final String  FAILED_MESSAGE = "failed";

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<MessageResponse> handleInvalidInputException(RuntimeException ex) {
		log.error("Invalid Input Exception: {}", ex.getMessage());
		log.info("exception is: {} ",  ex.getMessage());

		return new ResponseEntity<>(new MessageResponse(FAILED_MESSAGE,ex.getMessage(),java.time.LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Unauthorized.class })

	public ResponseEntity<MessageResponse> handleUnauthorizedException(Unauthorized ex) {
		log.error("Unauthorized Exception: {}", ex.getMessage());
		return new ResponseEntity<>(new MessageResponse(FAILED_MESSAGE,ex.getMessage(),java.time.LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<MessageResponse> handleException(Exception ex) {
		log.error("Exception: {}", ex.getMessage());
		return new ResponseEntity<>(new MessageResponse(FAILED_MESSAGE,ex.getMessage(),java.time.LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
