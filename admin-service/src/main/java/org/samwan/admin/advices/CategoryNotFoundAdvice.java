package org.samwan.admin.advices;

import org.samwan.admin.exceptions.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoryNotFoundAdvice {

    @ResponseBody//signals that this advice is rendered straight into the response body
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//404
    String categoryNotFoundHandler(CategoryNotFoundException e){
        return e.getMessage();
    }
}

