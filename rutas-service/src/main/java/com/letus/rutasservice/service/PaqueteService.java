package com.letus.rutasservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.dto.Envio;
import com.letus.rutasservice.model.Paquete;
import com.letus.rutasservice.repository.PaqueteRepository;
import com.letus.rutasservice.repository.RutaRepository;

@Service
public class PaqueteService {
    @Autowired
    PaqueteRepository paqueteRepository;
    @Autowired
    RutaRepository retuRepository;

    public Paquete inicializePackage(Envio envio){
        Paquete newPaquete= new Paquete((long)envio.getPackageId(), 0,
        retuRepository.getById((long)envio.getRutaId()));
        paqueteRepository.save(newPaquete);
        return newPaquete;
    }

    public Paquete movePackageInicial(int packageId){
        Paquete paquete= paqueteRepository.findById((long)packageId)
            .orElseThrow(() -> new RuntimeException("Error: Paquete not found"));
        paquete.setCheckpointNumberLocation(1);
        paqueteRepository.save(paquete);
        return paquete;
    }
}
