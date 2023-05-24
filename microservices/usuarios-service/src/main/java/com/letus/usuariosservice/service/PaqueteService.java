package com.letus.usuariosservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.dto.RecibirCheckEvent;
import com.letus.usuariosservice.dto.RecibirDTO;
import com.letus.usuariosservice.model.EState;
import com.letus.usuariosservice.model.Notificacion;
import com.letus.usuariosservice.model.Paquete;
import com.letus.usuariosservice.repository.NotificacionRepository;
import com.letus.usuariosservice.repository.PaqueteRepository;

@Service
public class PaqueteService {
    @Autowired
    PaqueteRepository paqueteRepository;

    @Autowired
    NotificacionRepository notificacionRepository;


    public void receivePaquete(RecibirDTO recibir){
        Paquete paquete = paqueteRepository.findById((long)recibir.getPackageId())
        .orElseThrow(() -> new RuntimeException("Error: Paquete not found"));
        if (paquete.getState()!=EState.READY_FOR_PICKUP) {
            throw new RuntimeException("Paquete not ready");
        }
        paquete.setState(EState.RECIEVED);
        paqueteRepository.save(paquete);
    }


    public void createNotification(RecibirCheckEvent event) {
        Paquete paquete = paqueteRepository.findById((long)event.getPackageId())
        .orElseThrow(() -> new RuntimeException("Error: Paquete not found"));

        paquete.setState(EState.READY_FOR_PICKUP);
        Notificacion notificacion = new Notificacion(event.getDate(),paquete,paquete.getUsuario());
        notificacionRepository.save(notificacion);
        paqueteRepository.save(paquete);

    }
}
