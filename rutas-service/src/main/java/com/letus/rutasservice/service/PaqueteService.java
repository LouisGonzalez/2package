package com.letus.rutasservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.dto.Envio;
import com.letus.rutasservice.model.Paquete;
import com.letus.rutasservice.model.Ruta;
import com.letus.rutasservice.repository.PaqueteRepository;
import com.letus.rutasservice.repository.RutaRepository;

@Service
public class PaqueteService {
    @Autowired
    PaqueteRepository paqueteRepository;
    @Autowired
    RutaRepository rutaRepository;

    public Paquete inicializePackage(Envio envio){
        Ruta ruta= rutaRepository.findById((long)envio.getRutaId())
            .orElseThrow(() -> new RuntimeException("Error: Ruta not found"));
        Paquete newPaquete= new Paquete((long)envio.getPackageId(),0, ruta);
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

    public void movePackageCheckpoint(int packageId, int checkpointNumber){
        Paquete paquete= paqueteRepository.findById((long)packageId)
            .orElseThrow(() -> new RuntimeException("Error: Paquete not found"));
        int place = paquete.getRuta().findCheckpoint(checkpointNumber);
        if(place == 0){
            throw new RuntimeException("Wrong checkpoint number"); 
        }else{
            paquete.setCheckpointNumberLocation(place);
            paqueteRepository.save(paquete);
        }
    }
}
