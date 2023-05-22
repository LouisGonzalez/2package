package com.letus.checkpointsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.checkpointsservice.model.CheckPoint;
import com.letus.checkpointsservice.repository.CheckpointRepository;
import com.letus.dto.Checkpoint;

@Service
public class CheckpointService {
    @Autowired
    CheckpointRepository checkpointRepository;

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

}
