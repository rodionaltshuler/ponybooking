package com.ottamotta.yunar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Can't accept return of this pony - it wasn't booked here!")
public class CantReturnPonyException extends RuntimeException {
}
