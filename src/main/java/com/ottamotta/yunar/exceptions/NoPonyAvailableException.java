package com.ottamotta.yunar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Pony is not available, either is booked or recharging")
public class NoPonyAvailableException extends RuntimeException {
}
