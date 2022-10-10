package fr.caensup.td3.messagerie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.caensup.td3.messagerie.rest.RestMessage;

@ControllerAdvice
public class RestControllerAdvice {

	@ResponseBody
	@JsonFormat
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(OrgaNotFoundException.class)
	public RestMessage orgaNotFound(OrgaNotFoundException ex) {
		return new RestMessage("404", ex.getMessage());
	}
}
