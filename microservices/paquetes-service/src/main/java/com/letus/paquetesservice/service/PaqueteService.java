package com.letus.paquetesservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.dto.Envio;
import com.letus.dto.PagoEvent;
import com.letus.paquetesservice.model.Paquete;
import com.letus.paquetesservice.repository.PaqueteRepository;

@Service
public class PaqueteService {
    @Autowired
    PaqueteRepository paqueteRepository;

    public  Paquete enviarPackage(Envio envio){
        int id =envio.getPackageId();
        try {
            Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new Exception("Package not found " ));
                paquete.setRutaId(envio.getRutaId());
                paquete.setStatus("TO ROUTE");
                Paquete updatedPaquete = paqueteRepository.save(paquete);
                return updatedPaquete;
        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }

    public void createPackage(){
        Paquete paquete= new Paquete((long)1,1,1,"PAID");
        paqueteRepository.save(paquete);
    }
    public void createPackage(PagoEvent pagoEvent){
        Paquete paquete= new Paquete(pagoEvent.getPago().getUserId(),"PAID");
        paqueteRepository.save(paquete);
    }
    public List<Paquete> getPackages() {
        return paqueteRepository.findAll();
    }
}
