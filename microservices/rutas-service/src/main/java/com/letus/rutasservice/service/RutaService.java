package com.letus.rutasservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.letus.dto.Checkpoint;
import com.letus.rutasservice.dto.CheckPointDTO;
import com.letus.rutasservice.dto.RutaDTO;
import com.letus.rutasservice.model.CheckPoint;
import com.letus.rutasservice.model.Paquete;
import com.letus.rutasservice.model.Ruta;
import com.letus.rutasservice.repository.CheckpointRepository;
import com.letus.rutasservice.repository.RutaRepository;

@Service
public class RutaService {
    @Autowired 
    RutaRepository rutaRepository;
    @Autowired 
    CheckpointRepository checkpointRepository;

    public Ruta enviarPaquete(Long idRuta, Paquete paquete){
        Ruta ruta = rutaRepository.getById(idRuta);
        ruta.addPaquete(paquete);
        Ruta updatedRuta = rutaRepository.save(ruta);
        return updatedRuta;

    }

    public List<Checkpoint> createList(List<CheckPoint> oldChecks,RutaDTO rutaDTO){
        List<Checkpoint> checkpoints=new ArrayList<Checkpoint>();
        for (int i = 0; i < oldChecks.size(); i++) {
            CheckPoint checkPoint = oldChecks.get(i);
            Checkpoint newCheckpoint = new Checkpoint();
            newCheckpoint.setId(checkPoint.getId());
            newCheckpoint.setName(checkPoint.getName());
            newCheckpoint.setAddress(rutaDTO.getCheckpoints().get(i).getAddress());
            checkpoints.add(newCheckpoint);
        }
        return checkpoints;
    }

    public List<Object[]> getRutas(){
        return rutaRepository.findAllRutas();
     }

    public List<CheckPoint> createRuta(RutaDTO rutaDTO){
        Ruta ruta = new Ruta(rutaDTO.getName(), rutaDTO.getStart(), rutaDTO.getFinish(),
         rutaDTO.getPrice(), rutaDTO.getNumberOfDays());
         rutaRepository.save(ruta);
        List<CheckPoint> checks=new ArrayList<CheckPoint>();
        int i =1;
        for (CheckPointDTO checkPoint : rutaDTO.getCheckpoints()) {

            checks.add(checkpointRepository.save(new CheckPoint(i,checkPoint.getName(),ruta)));
            
            i++;
        }
        ruta.setCheckpoints(checks);
        rutaRepository.save(ruta);
        return checks;
        
         
    }

    public void createRutaTest() {
        Ruta ruta = new Ruta((long) 300,"RutaA","P1","P2");
        rutaRepository.save(ruta);    
        
    } 
}
