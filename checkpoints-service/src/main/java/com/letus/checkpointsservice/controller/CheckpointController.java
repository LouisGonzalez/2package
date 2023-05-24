package com.letus.checkpointsservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.checkpointsservice.dto.MoverDTO;
import com.letus.checkpointsservice.dto.RecibirDTO;
import com.letus.checkpointsservice.kafka.CheckpointProducer;
import com.letus.checkpointsservice.kafka.RecibirProducer;
import com.letus.checkpointsservice.kafka.SigueinteProducer;
import com.letus.checkpointsservice.service.CheckpointService;
import com.letus.dto.MoverCheckEvent;

@RestController
@RequestMapping("/checkpoint")
public class CheckpointController {
    @Autowired
    CheckpointService checkpointService ;

    private CheckpointProducer checkpointProducer;

    private RecibirProducer recibirProducer;

    private SigueinteProducer sigueinteProducer;

    @GetMapping("/get-paquetes")
    public List<Object[]> getPaquetes() {
        return checkpointService.getCheckpoints();
    }

    @PostMapping("/receive-checkpoint")
    public String receiveAtCheckPoint(@RequestBody RecibirDTO recibirDTO){
        checkpointService.recibirEnCheckPoint(recibirDTO,recibirProducer,sigueinteProducer);
        return "Received successfully ...";
    }


    public CheckpointController(CheckpointProducer checkpointProducer, RecibirProducer recibirProducer, SigueinteProducer sigueinteProducer) {
        this.checkpointProducer = checkpointProducer;
        this.recibirProducer = recibirProducer;
        this.sigueinteProducer = sigueinteProducer;
    }

    @PostMapping("/move-checkpoint")
    public String moverPaqueteInicio (@RequestBody MoverDTO mover){
       MoverCheckEvent moverEvent = checkpointService.moverCheck(mover);
       checkpointProducer.sendMessage(moverEvent);
        return "Moved successfully ...";
    }
}
