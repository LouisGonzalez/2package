package com.letus.checkpointsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.checkpointsservice.dto.MoverDTO;
import com.letus.checkpointsservice.model.CheckPoint;
import com.letus.checkpointsservice.model.EStatus;
import com.letus.checkpointsservice.model.Movement;
import com.letus.checkpointsservice.model.Paquete;
import com.letus.checkpointsservice.repository.CheckpointRepository;
import com.letus.checkpointsservice.repository.MovementRepository;
import com.letus.checkpointsservice.repository.PaqueteRepository;
import com.letus.dto.Checkpoint;
import com.letus.dto.MoverCheckEvent;
import com.letus.dto.MoverEvent;

@Service
public class    CheckpointService {
    @Autowired
    CheckpointRepository checkpointRepository;

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    PaqueteRepository paqueteRepository;

    public void createCheckpoints(List<Checkpoint> checkpoints) {
        for (Checkpoint checkpoint : checkpoints) {
            CheckPoint newCheckPoint = new CheckPoint(checkpoint.getId(),
            checkpoint.getName(),checkpoint.getAddress());
            checkpointRepository.save(newCheckPoint);
        }        
    }
    public List<Object[]> getCheckpoints(){
        return checkpointRepository.findAllCheckPoints();
     }

     public void initializePaquete(MoverEvent moverEvent) {
        CheckPoint checkPoint = checkpointRepository.findById(moverEvent.getToCheckPointId())
        .orElseThrow(() -> new RuntimeException("Error: Checkpoint not found "+moverEvent.getToCheckPointId()));
        Paquete paquete = new Paquete((long)moverEvent.getPackageId(),moverEvent.getNextPointId(),
        moverEvent.getDestinationId(),checkPoint);
        Movement movement = new Movement(EStatus.PENDING, checkPoint, paquete, moverEvent.getDate(), true);
        paqueteRepository.save(paquete);
        movementRepository.save(movement);

     }

     public MoverCheckEvent moverCheck(MoverDTO mover){
        Paquete paquete = paqueteRepository.findById((long)mover.getPackageId())
        .orElseThrow(() -> new RuntimeException("Error: Checkpoint not found "+mover.getPackageId()));
        CheckPoint checkPoint = checkpointRepository.findById(paquete.getNexPointId())
        .orElseThrow(() -> new RuntimeException("Error: Checkpoint not found "+paquete.getNexPointId()));
        paquete.setCheckPoint(checkPoint);
        Movement movement = new Movement(EStatus.PENDING, checkPoint, paquete, mover.getDate(), true);
        paqueteRepository.save(paquete);
        movementRepository.save(movement);
        MoverCheckEvent event = new MoverCheckEvent();
        event.setStatus("PENDING");
        event.setMessage("order status is in pending state");
        event.setPackageId(mover.getPackageId());
        event.setCheckPointId(paquete.getNexPointId());
        return event;
     }

}
