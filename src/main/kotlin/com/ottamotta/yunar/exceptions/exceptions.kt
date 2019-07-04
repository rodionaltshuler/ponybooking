package com.ottamotta.yunar.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Can't accept return of this pony - it wasn't booked here!")
class CantReturnPonyException : RuntimeException()

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Pony is not available, either is booked or recharging")
class NoPonyAvailableException : RuntimeException()

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pony with such name hasn't been found")
class PonyDoesntExistsException : RuntimeException()
