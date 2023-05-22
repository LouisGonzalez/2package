package com.letus.pagosservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.dto.PagoEvent;
import com.letus.pagosservice.dto.PagoDTO;
import com.letus.pagosservice.exception.CardInvalid;
import com.letus.pagosservice.kafka.PagosProducer;
import com.letus.pagosservice.model.Pago;
import com.letus.pagosservice.repository.PagosRepository;

@Service
public class PagoService {
    @Autowired
    PagosRepository pagosRepository;

    public String validatePago(PagoDTO pago, PagosProducer pagosProducer ){
        if(validateCard(pago)){
            Pago newPago = new Pago(pago.getUserId(),pago.getPackageId(),
            pago.getAmount(),pago.getDate());

            pagosRepository.save(newPago);

            PagoEvent pagoEvent = new PagoEvent();
        
            pagoEvent.setStatus("PENDING");
            pagoEvent.setMessage("pago status is in pending state");
            pagoEvent.setPago(new com.letus.dto.Pago(pago.getUserId(),pago.getPackageId()));
            pagosProducer.sendMessage(pagoEvent);
            return "Pago Realizado!";
        }
        throw new CardInvalid(pago.getCardNumber()+" Card Invalid");
    }

    private boolean validateCard(PagoDTO pago){
        return true;
    }

    public List<Object[]> getPaquetes(){
        return pagosRepository.findAllPagos();
     }

}
