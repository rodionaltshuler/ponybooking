package com.ottamotta.yunar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Pony with such name hasn't been found")
public class PonyDoesntExistsException extends RuntimeException {

}
