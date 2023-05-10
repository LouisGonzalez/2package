package com.letus.rutasservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.letus.rutasservice.model.Paquete;
import com.letus.rutasservice.model.Ruta;
import com.letus.rutasservice.repository.RutaRepository;

@Service
public class RutaService {
    @Autowired 
    RutaRepository rutaRepository;

    public Ruta enviarPaquete(Long idRuta, Paquete paquete){
        Ruta ruta = rutaRepository.getById(idRuta);
        ruta.addPaquete(paquete);
        Ruta updatedRuta = rutaRepository.save(ruta);
        return updatedRuta;

    }

    public void createRutaTest() {
        Ruta ruta = new Ruta((long) 300,"RutaA","P1","P2");
        rutaRepository.save(ruta);    
        
    } 
}
