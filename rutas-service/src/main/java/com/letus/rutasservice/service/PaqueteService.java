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
        Paquete newPaquete= new Paquete((long)envio.getPackageId(), 1,
        retuRepository.getById((long)envio.getRutaId()));
        return paqueteRepository.save(newPaquete);
    }
}
