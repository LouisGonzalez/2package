package com.letus.pagosservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.letus.dto.PagoEvent;
import com.letus.pagosservice.dto.PagoDTO;
import com.letus.pagosservice.exception.CardInvalid;
import com.letus.pagosservice.kafka.PagosProducer;
import com.letus.pagosservice.service.PagoService;

@RestController
@RequestMapping("/pago")
public class PagoController {
   
    private PagosProducer pagosProducer;
    @Autowired
    PagoService pagoService;

    public PagoController(PagosProducer pagosProducer) {
        this.pagosProducer = pagosProducer;
    }

    @PostMapping("/pagar")
    public ResponseEntity<?> placeOrder(@RequestBody PagoDTO pago){
        String validate = pagoService.validatePago(pago, pagosProducer);
        return new ResponseEntity<String>(validate, HttpStatus.OK);
    }

    @GetMapping("/get-pagos")
    public List<Object[]> getPagos() {
        return pagoService.getPaquetes();
    }

}
