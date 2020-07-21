package org.samwan.admin.advices;

import org.samwan.admin.exceptions.LocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String locationNotFoundHandler(LocationNotFoundException e){
        return e.getMessage();
    }
}
