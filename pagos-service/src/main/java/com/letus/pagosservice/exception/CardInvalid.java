package com.letus.pagosservice.exception;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardInvalid extends ResponseStatusException  {
    public CardInvalid(String message){
        super(HttpStatus.NOT_ACCEPTABLE, message);
      }
    
      
}
