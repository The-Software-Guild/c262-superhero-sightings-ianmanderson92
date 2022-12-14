/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 12:25 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: GlobalExceptionHandler
 * @Full_Class_Name: com.sg.superhero.controller.GlobalExceptionHandler
 * @File_Name: GlobalExceptionHandler.java
 */

/*
 * @Author: Hedi Benyounes
 * @Mailto: https://blog.tericcabrel.com/validate-request-body-and-parameter-in-spring-boot/
 * @Modified: 9/8/22, 12:24 AM
 *
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: GlobalExceptionHandler
 * @Full_Class_Name: com.sg.superhero.controller.GlobalExceptionHandler
 * @File_Name: GlobalExceptionHandler.java
 */

package com.sg.superhero.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        Map<String,List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map( DefaultMessageSourceResolvable::getDefaultMessage)
            .collect( Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
